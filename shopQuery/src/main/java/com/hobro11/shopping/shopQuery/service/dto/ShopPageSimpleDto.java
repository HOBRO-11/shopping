package com.hobro11.shopping.shopQuery.service.dto;

import java.net.URI;

import com.hobro11.shopping.sales.Address;
import com.hobro11.shopping.sales.QShopPage;
import com.hobro11.shopping.sales.ShopPageStatue;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.Getter;

@Getter
public class ShopPageSimpleDto {

    private Long id;
    private String title;
    private String description;
    private URI thumbnailUri;
    private ShopPageStatue status;
    private Address address;

    public ShopPageSimpleDto(Long id, String title, String description, URI thumbnailUri, ShopPageStatue status,
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
