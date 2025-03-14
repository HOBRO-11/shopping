package com.hobro11.shopping.orders.service;

import com.hobro11.shopping.orders.OrdersStatus;
import com.hobro11.shopping.orders.exception.OrdersNotFoundException;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;
import com.hobro11.shopping.orders.service.dto.OrdersReadOnly;

public interface OrdersWriter {

    OrdersReadOnly findOrdersReadOnlyByOrderNumber(Long orderNumber) throws OrdersNotFoundException;

    Long createOrders(OrdersCreateDto dto);

    void updateStatus(Long orderNumber, OrdersStatus status);

    void updateCheckSum(Long orderNumber, Long checkSum);

    void deleteOrders(Long orderNumber);

}