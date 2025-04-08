package com.hobro11.command.domain.orders.service.dto;

import java.util.Set;

import com.hobro11.command.domain.orders.OptionQuantity;
import com.hobro11.command.domain.orders.Orders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrdersCreateDto {

    private final Long memberId;
    private final Long shopPageId;
    private final Set<OptionQuantity> optionQuantities;
    private final Long price;

    public Orders toEntity(Long orderNumber) {
        return Orders.builder()
                .orderNumber(orderNumber)
                .memberId(memberId)
                .shopPageId(shopPageId)
                .optionQuantities(optionQuantities)
                .price(price)
                .build();
    }
}
