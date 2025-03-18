package com.hobro11.shopping.shopQuery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.shopQuery.service.CartQueryService;
import com.hobro11.shopping.shopQuery.service.dto.CartDetailDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/carts")
@RestController
@RequiredArgsConstructor
public class CartControlller {

    private final CartQueryService cartQueryService;

    @GetMapping("/{memberId}")
    public CartDetailDto getCart(@PathVariable Long memberId) {
        return cartQueryService.getCart(memberId);
    }

}
