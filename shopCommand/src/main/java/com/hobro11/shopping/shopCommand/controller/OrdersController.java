package com.hobro11.shopping.shopCommand.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.orders.OrdersStatus;
import com.hobro11.shopping.shopCommand.controller.form.OrdersCreateForm;
import com.hobro11.shopping.shopCommand.service.OrdersCommandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersCommandService ordersCommandService;

    @PostMapping
    public Long createOrder(@Valid @RequestBody OrdersCreateForm form) {
        return ordersCommandService.createOrder(form.toDto());
    }

    @PatchMapping("/{orderNumber}/status")
    public void updateStatus(@PathVariable Long orderNumber, @NotNull OrdersStatus status) {
        ordersCommandService.updateStatus(orderNumber, status);
    }

    @PatchMapping("/{orderNumber}/checkSum")
    public void updateCheckSum(@PathVariable Long orderNumber, @NotNull Long saleOptionId, @Positive Integer quantity) {
        ordersCommandService.updateCheckSum(orderNumber, saleOptionId, quantity);
    }

    @DeleteMapping("/{orderNumber}")
    public void deleteOrders(@PathVariable Long orderNumber) {
        ordersCommandService.deleteOrders(orderNumber);
    }
}
