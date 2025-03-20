package com.hobro11.shopping.sales.service.dto;

import java.net.URI;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.sales.Address;
import com.hobro11.shopping.sales.ShopPage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShopPageCreateDto {

    private final Long memberId;
    private final String title;
    private final URI thumbnailUri;
    private final String description;
    private final Address address;

    public ShopPage toEntity() {
        return ShopPage.builder()
                .memberId(memberId)
                .title(title)
                .thumbnailUri(thumbnailUri)
                .description(description)
                .status(ShopPageStatue.ACTIVE)
                .address(address)
                .build();
    }

}
