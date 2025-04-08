package com.hobro11.command.service;

import com.hobro11.command.domain.orders.OrdersStatus;
import com.hobro11.command.domain.orders.service.dto.OrdersCreateDto;

public interface OrdersCommandService {

    Long createOrder(OrdersCreateDto dto);

    void updateStatus(Long id, OrdersStatus status);

    void updateCheckSum(Long id, Long saleOptionId, int quantity);

    void deleteOrders(Long id);

}