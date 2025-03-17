package com.hobro11.shopping.shopQuery.service.dto;

import com.hobro11.shopping.sales.QSaleOption;
import com.hobro11.shopping.sales.SaleOptionStatus;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleOptionSimpleDto {
    private Long id;
    private String name;
    private Long shopPageId;
    private SaleOptionStatus status;
    private Integer price;

    public static Expression<SaleOptionSimpleDto> asDto() {
        QSaleOption saleOption = QSaleOption.saleOption;

        return Projections.constructor(SaleOptionSimpleDto.class,
                saleOption.id,
                saleOption.name,
                saleOption.shopPage.id,
                saleOption.status,
                saleOption.price);
    }
}
