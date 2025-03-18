package com.hobro11.shopping.shopQuery.service.dto;

import java.util.List;
import java.util.Set;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.QCart;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CartDetailDto {

    private Long id;
    private Long memberId;
    private Set<OptionQuantity> optionQuantities;
    @Setter
    private List<SaleOptionSimpleDto> saleOptionSimples;
    @Setter
    private List<ShopPageSimpleDto> shopPageSimples;

    public CartDetailDto(Long id, Long memberId, Set<OptionQuantity> optionQuantities) {
        this.id = id;
        this.memberId = memberId;
        this.optionQuantities = optionQuantities;
    }

    public static Expression<CartDetailDto> asDto() {
        QCart cart = QCart.cart;
        return Projections.constructor(CartDetailDto.class,
                cart.id,
                cart.memberId,
                cart.optionQuantities);
    }

}
