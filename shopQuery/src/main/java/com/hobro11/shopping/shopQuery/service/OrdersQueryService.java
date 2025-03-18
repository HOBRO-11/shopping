package com.hobro11.shopping.shopQuery.service;

import java.util.List;

import com.hobro11.shopping.shopQuery.service.dto.OrdersDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.OrdersSimpleDto;

public interface OrdersQueryService {

    List<OrdersSimpleDto> getOrdersSimples(Long memberId);
    OrdersDetailDto getOrderDetail(Long memberId, Long orderNumber);

}