package com.hobro11.command.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.command.domain.orders.OrdersStatus;
import com.hobro11.command.service.OrdersCommandService;
import com.hobro11.command.web.form.OrdersCreateForm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
@Validated
public class OrdersController {

    private final OrdersCommandService ordersCommandService;
    private static final String AUTH_CHECK_EX = "@cartAuthHandler.check(#memberId)";

    @PostMapping
    @PreAuthorize(AUTH_CHECK_EX)
    public Long createOrder(@Valid @RequestBody final OrdersCreateForm form) {
        return ordersCommandService.createOrder(form.toDto());
    }

    @PatchMapping("/{orderNumber}/status")
    @PreAuthorize(AUTH_CHECK_EX)
    public void updateStatus(
            @PathVariable("orderNumber") final Long orderNumber,
            @NotNull @RequestParam("status") final OrdersStatus status) {
        ordersCommandService.updateStatus(orderNumber, status);
    }

    @PatchMapping("/{orderNumber}/checkSum")
    @PreAuthorize(AUTH_CHECK_EX)
    public void updateCheckSum(@PathVariable("orderNumber") final Long orderNumber,
            @NotNull @RequestParam("saleOptionId") final Long saleOptionId,
            @Positive @RequestParam("quantity") final Integer quantity) {
        ordersCommandService.updateCheckSum(orderNumber, saleOptionId, quantity);
    }

    @DeleteMapping("/{orderNumber}")
    @PreAuthorize(AUTH_CHECK_EX)
    public void deleteOrders(@PathVariable("orderNumber") final Long orderNumber) {
        ordersCommandService.deleteOrders(orderNumber);
    }
}
