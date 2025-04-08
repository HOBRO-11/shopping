package com.hobro11.query.service.dto;

import java.net.URI;

import com.hobro11.query.model.Address;
import com.hobro11.query.model.QShopPage;
import com.hobro11.query.model.ShopPageStatus;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.Getter;

@Getter
public class ShopPageSimpleDto {

    private Long id;
    private String title;
    private String description;
    private URI thumbnailUri;
    private ShopPageStatus status;
    private Address address;

    public ShopPageSimpleDto(Long id, String title, String description, URI thumbnailUri, ShopPageStatus status,
            Address address) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailUri = thumbnailUri;
        this.status = status;
        this.address = address;
    }

    public static Expression<ShopPageSimpleDto> asDto() {
        QShopPage shopPage = QShopPage.shopPage;
        return Projections.constructor(ShopPageSimpleDto.class,
                shopPage.id,
                shopPage.title,
                shopPage.description,
                shopPage.thumbnailUri,
                shopPage.status,
                shopPage.address);
    }

}
