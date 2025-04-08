package com.hobro11.query.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.query.service.CartQueryService;
import com.hobro11.query.service.dto.CartDetailDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/carts")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartQueryService cartQueryService;

    @GetMapping("/{memberId}")
    public CartDetailDto getCart(@PathVariable("memberId") final Long memberId) {
        return cartQueryService.getCart(memberId);
    }

}
