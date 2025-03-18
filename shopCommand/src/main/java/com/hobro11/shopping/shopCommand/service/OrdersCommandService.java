package com.hobro11.shopping.shopCommand.service;

import com.hobro11.shopping.orders.OrdersStatus;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;

public interface OrdersCommandService {

    Long createOrder(OrdersCreateDto dto);

    void updateStatus(Long id, OrdersStatus status);

    void updateCheckSum(Long id, Long saleOptionId, int quantity);

    void deleteOrders(Long id);

}