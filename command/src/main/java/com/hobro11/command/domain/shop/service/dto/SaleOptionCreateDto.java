package com.hobro11.command.domain.shop.service.dto;

import com.hobro11.command.domain.shop.SaleOption;
import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.ShopPage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaleOptionCreateDto {

    private final String name;
    private final Long shopPageId;
    private final String description;
    private final SaleOptionStatus status;
    private final Integer price;

    public SaleOption toEntity(ShopPage shopPage) {
        return SaleOption.builder()
                .name(name)
                .shopPage(shopPage)
                .description(description)
                .status(status)
                .price(price)
                .build();
    }

}
