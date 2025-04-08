package com.hobro11.query.service;

import java.util.List;

import com.hobro11.query.service.dto.OrdersDetailDto;
import com.hobro11.query.service.dto.OrdersSimpleDto;

public interface OrdersQueryService {

    List<OrdersSimpleDto> getOrdersSimples(Long memberId);
    OrdersDetailDto getOrderDetail(Long memberId, Long orderNumber);

}