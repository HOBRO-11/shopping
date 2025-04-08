package com.hobro11.query.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.query.service.OrdersQueryService;
import com.hobro11.query.service.dto.OrdersDetailDto;
import com.hobro11.query.service.dto.OrdersSimpleDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersQueryService ordersQueryService;

    @GetMapping("/{memberId}")
    public List<OrdersSimpleDto> getOrders(@PathVariable("memberId") final Long memberId) {
        return ordersQueryService.getOrdersSimples(memberId);
    }

    @GetMapping("/{memberId}/{orderNumber}")
    public OrdersDetailDto getOrderDetail(
            @PathVariable("memberId") final Long memberId,
            @PathVariable("orderNumber") final Long orderNumber) {
        return ordersQueryService.getOrderDetail(memberId, orderNumber);
    }
}
