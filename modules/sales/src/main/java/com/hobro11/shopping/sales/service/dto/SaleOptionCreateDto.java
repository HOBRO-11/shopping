package com.hobro11.shopping.sales.service.dto;

import com.hobro11.shopping.sales.SaleOption;
import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.ShopPage;

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
