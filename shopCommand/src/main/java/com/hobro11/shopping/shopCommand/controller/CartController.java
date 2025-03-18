package com.hobro11.shopping.shopCommand.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.shopCommand.service.CartCommandService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/carts")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartCommandService cartCommandService;

    @PatchMapping("/{memberId}/add")
    public void createCart(@PathVariable Long memberId, @NotNull Long saleOptionId, @Positive Integer quantity) {
        cartCommandService.addSaleOptionAtCart(memberId, saleOptionId, quantity);
    }

    @PatchMapping("/{memberId}/remove")
    public void removeCart(@PathVariable Long memberId, @NotEmpty @RequestBody List<Long> saleOptionIds) {
        cartCommandService.removeSaleOptionAtCart(memberId, saleOptionIds);
    }

    @DeleteMapping("/{memberId}")
    public void deleteCart(@PathVariable Long memberId) {
        cartCommandService.deleteCartByMemberId(memberId);
    }

}
