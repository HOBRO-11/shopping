package com.hobro11.shopping.shopQuery.service.dto;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.locationtech.jts.geom.Point;

import com.hobro11.shopping.members.QMember;
import com.hobro11.shopping.sales.QShopPage;
import com.hobro11.shopping.sales.ShopPageStatue;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ShopPageDetailDto {

    private Long id;
    private Long memberId;
    private Long brn;
    private String name;
    private String phone;
    private String title;
    private URI thumbnailUri;
    private String description;
    private ShopPageStatue status;
    private LocationDto location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Setter
    private List<SaleOptionDetailDto> saleOptionSimples;

    public ShopPageDetailDto(Long id, Long memberId, Long brn, String name, String phone, String title,
            URI thumbnailUri, String description, ShopPageStatue status, Point location, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.memberId = memberId;
        this.brn = brn;
        this.name = name;
        this.phone = phone;
        this.title = title;
        this.thumbnailUri = thumbnailUri;
        this.description = description;
        this.status = status;
        this.location = new LocationDto(location.getX(), location.getY());
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public static Expression<ShopPageDetailDto> asDto() {
        QShopPage shopPage = QShopPage.shopPage;
        QMember member = QMember.member;

        return Projections.constructor(ShopPageDetailDto.class,
                shopPage.id,
                shopPage.memberId,
                member.brn,
                member.name,
                member.phone,
                shopPage.title,
                shopPage.thumbnailUri,
                shopPage.description,
                shopPage.status,
                shopPage.location,
                shopPage.createdAt,
                shopPage.updatedAt);
    }
}
