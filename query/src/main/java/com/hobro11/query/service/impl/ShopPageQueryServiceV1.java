package com.hobro11.query.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hobro11.query.model.QMember;
import com.hobro11.query.model.QSaleOption;
import com.hobro11.query.model.QShopPage;
import com.hobro11.query.model.ShopPageStatus;
import com.hobro11.query.service.ShopPageQueryService;
import com.hobro11.query.service.dto.SaleOptionDetailDto;
import com.hobro11.query.service.dto.ShopPageDetailDto;
import com.hobro11.query.service.dto.ShopPageSimpleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopPageQueryServiceV1 implements ShopPageQueryService {

    private final JPAQueryFactory queryFactory;
    private QShopPage shopPage = QShopPage.shopPage;
    private QSaleOption saleOption = QSaleOption.saleOption;
    private QMember member = QMember.member;

    @Override
    public List<ShopPageSimpleDto> getShopPageSimples(@Nonnull List<Long> shopPageIds, ShopPageStatus status) {
        return queryFactory
                .select(ShopPageSimpleDto.asDto())
                .from(shopPage)
                .where(shopPage.id.in(shopPageIds).and(shopPage.status.eq(status)))
                .fetch();
    }

    @Override
    public Optional<ShopPageDetailDto> getShopPageDetail(Long shopPageId) {
        ShopPageDetailDto findShopPage = queryFactory
                .select(ShopPageDetailDto.asDto())
                .from(shopPage)
                .join(member)
                .on(shopPage.memberId.eq(member.id))
                .where(shopPage.id.eq(shopPageId))
                .fetchFirst();

        if (findShopPage == null) {
            return Optional.empty();
        }

        List<SaleOptionDetailDto> saleOptions = queryFactory
                .select(SaleOptionDetailDto.asDto())
                .from(saleOption)
                .where(saleOption.shopPage.id.eq(shopPageId))
                .fetch();

        findShopPage.setSaleOptionSimples(saleOptions);

        return Optional.ofNullable(findShopPage);
    }

}
