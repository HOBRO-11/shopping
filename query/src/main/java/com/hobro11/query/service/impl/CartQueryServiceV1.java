package com.hobro11.query.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hobro11.query.model.QCart;
import com.hobro11.query.model.QSaleOption;
import com.hobro11.query.model.QShopPage;
import com.hobro11.query.service.CartQueryService;
import com.hobro11.query.service.dto.CartDetailDto;
import com.hobro11.query.service.dto.SaleOptionSimpleDto;
import com.hobro11.query.service.dto.ShopPageSimpleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartQueryServiceV1 implements CartQueryService {

    private final JPAQueryFactory queryFactory;
    private QCart cart = QCart.cart;
    private QShopPage shopPage = QShopPage.shopPage;
    private QSaleOption saleOption = QSaleOption.saleOption;

    @Override
    public CartDetailDto getCart(Long memberId) {
        CartDetailDto cartDto = queryFactory
                .select(CartDetailDto.asDto())
                .from(cart)
                .where(cart.memberId.eq(memberId))
                .fetchFirst();

        if (cartDto == null) {
            return null;
        }

        List<SaleOptionSimpleDto> sosds = getSaleOptionSimples(cartDto);
        List<ShopPageSimpleDto> spsds = getShopPageSimples(sosds);

        cartDto.setSaleOptionSimples(sosds);
        cartDto.setShopPageSimples(spsds);

        return cartDto;
    }

    private List<SaleOptionSimpleDto> getSaleOptionSimples(CartDetailDto cartDto) {
        Set<Long> saleOptionIds = new HashSet<>();
        cartDto.getOptionQuantities().forEach(oq -> saleOptionIds.add(oq.getSaleOptionId()));

        return queryFactory
                .select(SaleOptionSimpleDto.asDto())
                .from(saleOption)
                .where(saleOption.id.in(saleOptionIds))
                .fetch();
    }

    private List<ShopPageSimpleDto> getShopPageSimples(List<SaleOptionSimpleDto> saleOptions) {
        Set<Long> shopPageIds = new HashSet<>();
        saleOptions.forEach(saleOption -> shopPageIds.add(saleOption.getShopPageId()));

        return queryFactory
                .select(ShopPageSimpleDto.asDto())
                .from(shopPage)
                .where(shopPage.id.in(shopPageIds))
                .fetch();
    }
}
