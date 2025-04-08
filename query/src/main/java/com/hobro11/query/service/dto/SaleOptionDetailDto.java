package com.hobro11.query.service.dto;

import java.time.LocalDateTime;

import com.hobro11.query.model.QSaleOption;
import com.hobro11.query.model.SaleOptionStatus;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleOptionDetailDto {
    private Long id;
    private String name;
    private String description;
    private Long shopPageId;
    private SaleOptionStatus status;
    private Integer price;
    private LocalDateTime createdAt;

    public static Expression<SaleOptionDetailDto> asDto() {
        QSaleOption saleOption = QSaleOption.saleOption;

        return Projections.constructor(SaleOptionDetailDto.class,
                saleOption.id,
                saleOption.name,
                saleOption.description,
                saleOption.shopPage.id,
                saleOption.status,
                saleOption.price,
                saleOption.createdAt);
    }
}
