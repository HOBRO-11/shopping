package com.hobro11.shopping.orders.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.hobro11.shopping.orders.OrdersStatus;
import com.hobro11.shopping.orders.exception.OrderNumberGenerateFailedException;
import com.hobro11.shopping.orders.exception.OrdersCheckSumExceededException;
import com.hobro11.shopping.orders.exception.OrdersNotFoundException;
import com.hobro11.shopping.orders.properties.OrdersProperties;
import com.hobro11.shopping.orders.repository.OrdersRepo;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;
import com.hobro11.shopping.orders.service.dto.OrdersReadOnly;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersWriterV1 implements OrdersWriter {

    private final OrdersRepo ordersRepo;
    private final OrdersProperties orderProperties;

    @Override
    public OrdersReadOnly findOrdersReadOnlyByOrderNumber(Long orderNumber) throws OrdersNotFoundException {
        return ordersRepo.findOrdersReadOnlyByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrdersNotFoundException());
    }

    @Override
    public Long createOrders(OrdersCreateDto dto) {
        Long orderNumber = generateOrderNumber();
        return ordersRepo.save(dto.toEntity(orderNumber)).getOrderNumber();
    }

    @Override
    public void updateStatus(Long orderNumber, OrdersStatus status) {
        ordersRepo.findByOrderNumber(orderNumber).ifPresent(orders -> {
            orders.setStatus(status);
        });
    }

    @Override
    public void updateCheckSum(Long orderNumber, Long checkSum) {
        ordersRepo.findByOrderNumber(orderNumber).ifPresent(orders -> {
            Long newCheckSum = orders.getCheckSum() - checkSum;

            if (newCheckSum < 0) {
                throw new OrdersCheckSumExceededException();
            }

            orders.setCheckSum(newCheckSum);
        });
    }

    @Override
    public void deleteOrders(Long orderNumber) {
        ordersRepo.deleteByOrderNumber(orderNumber);
    }

    private Long generateOrderNumber() {
        String dateFormat = orderProperties.getDateFormat();
        int subfixLength = orderProperties.getSubfixLength();
        int tryCount = orderProperties.getTryCount();

        String orderNumberPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));
        Long orderNumber = 0L;
        int count = 0;

        while (count++ < tryCount) {

            String randomNum = String.valueOf(System.nanoTime() % 1_000_000);
            StringBuilder sb = new StringBuilder(orderNumberPrefix);

            if (randomNum.length() < subfixLength) {
                sb.append("0");
                sb.append(randomNum);
            } else {
                sb.append(randomNum);
            }

            orderNumber = Long.valueOf(sb.toString());

            if (isOrderNumberNonExists(orderNumber) == false) {
                return orderNumber;
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new OrderNumberGenerateFailedException();
            }
        }

        throw new OrderNumberGenerateFailedException();
    }

    private boolean isOrderNumberNonExists(Long orderNumber) {
        return ordersRepo.existsByOrderNumber(orderNumber);
    }
}
