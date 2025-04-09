package com.hobro11.command.domain.orders.service;

import com.hobro11.command.domain.orders.OrdersStatus;
import com.hobro11.command.core.exception.exceptions.OrdersNotFoundException;
import com.hobro11.command.domain.orders.service.dto.OrdersCreateDto;
import com.hobro11.command.domain.orders.service.dto.OrdersReadOnly;

public interface OrdersService {

    OrdersReadOnly findOrdersReadOnlyByOrderNumber(Long orderNumber) throws OrdersNotFoundException;

    Long createOrders(OrdersCreateDto dto);

    void updateStatus(Long orderNumber, OrdersStatus status);

    void updateCheckSum(Long orderNumber, Long checkSum);

    void deleteOrders(Long orderNumber);

}