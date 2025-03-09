package com.hobro11.shopping.orders.service;

import com.hobro11.shopping.orders.OrdersStatus;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;

public interface OrdersWriter {

    Long createOrders(OrdersCreateDto dto);

    void updateStatus(Long orderNumber, OrdersStatus status);

    void updateCheckSum(Long orderNumber, Long checkSum);

    void deleteOrders(Long orderNumber);

}