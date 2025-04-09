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
import com.hobro11.command.domain.orders.service.OrdersService;
import com.hobro11.command.domain.orders.service.dto.OrdersCreateDto;
import com.hobro11.command.domain.orders.service.dto.OrdersReadOnly;
import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.service.SaleOptionService;
import com.hobro11.command.domain.shop.service.dto.SaleOptionReadOnly;
import com.hobro11.command.service.OrdersCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersCommandServiceV1 implements OrdersCommandService {

    private final OrdersService ordersService;
    private final SaleOptionService saleOptionService;

    @Override
    public Long createOrder(OrdersCreateDto dto) {
        checkAndPrice(dto);
        return ordersService.createOrders(dto);
    }

    private void checkAndPrice(OrdersCreateDto dto) {
        Set<OptionQuantity> optionQuantities = dto.getOptionQuantities();

        int result = 0;
        for (OptionQuantity oq : optionQuantities) {
            Long saleOptionId = oq.getSaleOptionId();
            SaleOptionReadOnly saleOption = saleOptionService.findSaleOptionReadOnlyById(saleOptionId);
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
        OrdersReadOnly orders = ordersService.findOrdersReadOnlyByOrderNumber(orderNumber);
        checkStatusChangeable(orders, status);
        ordersService.updateStatus(orderNumber, status);
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
        OrdersReadOnly orders = ordersService.findOrdersReadOnlyByOrderNumber(orderNumber);
        checkOptionQuantity(orders, saleOptionId, quantity);
        Long checkSum = getCheckSumWithSaleOption(saleOptionId, quantity);
        ordersService.updateCheckSum(orderNumber, checkSum);
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
        SaleOptionReadOnly saleOption = saleOptionService.findSaleOptionReadOnlyById(saleOptionId);
        return saleOption.getPrice() * quantity;
    }

    @Override
    public void deleteOrders(Long orderNumber) {
        ordersService.deleteOrders(orderNumber);
    }
}
