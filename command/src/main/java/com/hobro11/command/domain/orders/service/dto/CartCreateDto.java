package com.hobro11.command.domain.orders.service.dto;

import com.hobro11.command.domain.orders.Cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartCreateDto {

    private final Long memberId;

    public Cart toEntity() {
        return Cart.builder().memberId(memberId).build();
    }
}
