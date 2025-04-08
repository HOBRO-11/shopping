package com.hobro11.command.service.impl;

import static com.hobro11.command.domain.orders.OrdersStatus.CANCELED;
import static com.hobro11.command.domain.orders.OrdersStatus.PAID;
import static com.hobro11.command.domain.orders.OrdersStatus.REFUNDED;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.command.domain.orders.OptionQuantity;
import com.hobro11.command.domain.orders.OrdersStatus;
import com.hobro11.command.domain.orders.service.OrdersWriter;
import com.hobro11.command.domain.orders.service.dto.OrdersCreateDto;
import com.hobro11.command.domain.orders.service.dto.OrdersReadOnly;
import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.service.SaleOptionWriter;
import com.hobro11.command.domain.shop.service.dto.SaleOptionReadOnly;
import com.hobro11.command.service.OrdersCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersCommandServiceV1 implements OrdersCommandService {

    private final OrdersWriter ordersWriter;
    private final SaleOptionWriter saleOptionWriter;

    @Override
    public Long createOrder(OrdersCreateDto dto) {
        checkAndPrice(dto);
        return ordersWriter.createOrders(dto);
    }

    private void checkAndPrice(OrdersCreateDto dto) {
        Set<OptionQuantity> optionQuantities = dto.getOptionQuantities();

        int result = 0;
        for (OptionQuantity oq : optionQuantities) {
            Long saleOptionId = oq.getSaleOptionId();
            SaleOptionReadOnly saleOption = saleOptionWriter.findSaleOptionReadOnlyById(saleOptionId);
            if (saleOption.getStatus() == SaleOptionStatus.SOLD_OUT
                    || saleOption.getStatus() == SaleOptionStatus.INACTIVE) {
                throw new IllegalStateException("this sale option is not available");
            }
            result += saleOption.getPrice() * oq.getQuantity();
        }

        if (dto.getPrice() != result) {
            throw new IllegalArgumentException("Invalid price");
        }
    }

    @Override
    public void updateStatus(Long orderNumber, OrdersStatus status) {
        OrdersReadOnly orders = ordersWriter.findOrdersReadOnlyByOrderNumber(orderNumber);
        checkStatusChangeable(orders, status);
        ordersWriter.updateStatus(orderNumber, status);
    }

    private void checkStatusChangeable(OrdersReadOnly orders, OrdersStatus status) {
        boolean changeable = switch (orders.getStatus()) {
            case IN_PAYMENT -> status == PAID || status == CANCELED;
            case PAID -> status == CANCELED || status == REFUNDED;
            case CANCELED -> false;
            case REFUNDED -> false;
            default -> false;
        };

        if (!changeable) {
            throw new IllegalStateException("Invalid status can't change");
        }
    }

    @Override
    public void updateCheckSum(Long orderNumber, Long saleOptionId, int quantity) {
        OrdersReadOnly orders = ordersWriter.findOrdersReadOnlyByOrderNumber(orderNumber);
        checkOptionQuantity(orders, saleOptionId, quantity);
        Long checkSum = getCheckSumWithSaleOption(saleOptionId, quantity);
        ordersWriter.updateCheckSum(orderNumber, checkSum);
    }

    private void checkOptionQuantity(OrdersReadOnly orders, Long saleOptionId, int quantity) {
        Optional<OptionQuantity> first = orders.getOptionQuantities().stream()
                .filter(optionQuantity -> optionQuantity.getSaleOptionId().equals(saleOptionId))
                .filter(optionQuantity -> optionQuantity.getQuantity() >= quantity)
                .findFirst();

        if (first == null || first.isEmpty()) {
            throw new IllegalArgumentException("Invalid sale option id or quantity");
        }
    }

    private Long getCheckSumWithSaleOption(Long saleOptionId, int quantity) {
        SaleOptionReadOnly saleOption = saleOptionWriter.findSaleOptionReadOnlyById(saleOptionId);
        return saleOption.getPrice() * quantity;
    }

    @Override
    public void deleteOrders(Long orderNumber) {
        ordersWriter.deleteOrders(orderNumber);
    }
}
