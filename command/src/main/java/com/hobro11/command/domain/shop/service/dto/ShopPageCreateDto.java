package com.hobro11.command.domain.shop.service.dto;

import java.net.URI;

import com.hobro11.command.domain.shop.Address;
import com.hobro11.command.domain.shop.ShopPage;
import com.hobro11.command.domain.shop.ShopPageStatus;

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
    private final Integer zoneNo;

    public ShopPage toEntity() {
        return ShopPage.builder()
                .memberId(memberId)
                .title(title)
                .thumbnailUri(thumbnailUri)
                .description(description)
                .status(ShopPageStatus.PENDING)
                .address(address)
                .build();
    }

}
