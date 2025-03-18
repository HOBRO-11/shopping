package com.hobro11.shopping.shopQuery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.shopQuery.service.OrdersQueryService;
import com.hobro11.shopping.shopQuery.service.dto.OrdersDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.OrdersSimpleDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {
    
    private final OrdersQueryService ordersQueryService;

    @GetMapping("/{memberId}")
    public List<OrdersSimpleDto> getOrders(@PathVariable Long memberId) {
        return ordersQueryService.getOrdersSimples(memberId);
    }

    @GetMapping("/{memberId}/{orderNumber}")
    public OrdersDetailDto getOrderDetail(@PathVariable Long memberId, @PathVariable Long orderNumber) {
        return ordersQueryService.getOrderDetail(memberId, orderNumber);
    }
}
