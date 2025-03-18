package com.hobro11.shopping.shopQuery.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hobro11.shopping.orders.QOrders;
import com.hobro11.shopping.sales.QSaleOption;
import com.hobro11.shopping.sales.QShopPage;
import com.hobro11.shopping.shopQuery.service.OrdersQueryService;
import com.hobro11.shopping.shopQuery.service.dto.OrdersDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.OrdersSimpleDto;
import com.hobro11.shopping.shopQuery.service.dto.SaleOptionSimpleDto;
import com.hobro11.shopping.shopQuery.service.dto.ShopPageSimpleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceV1 implements OrdersQueryService {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private QOrders orders = QOrders.orders;
    private QShopPage shopPage = QShopPage.shopPage;
    private QSaleOption saleOption = QSaleOption.saleOption;

    @Override
    @SuppressWarnings("unchecked")
    public List<OrdersSimpleDto> getOrdersSimples(Long memberId) {
        final String ordersSimpleGetSQL = "SELECT o.order_number, o.member_id, o.shop_page_id, " +
                "(o.option_quantities -> 0 ->> 'saleOptionId')::BIGINT AS sale_option_id, " +
                "o.price, o.status, o.created_at, o.updated_at " +
                "FROM orders AS o WHERE o.member_id = :memberId";

        Query nativeQuery = entityManager.createNativeQuery(ordersSimpleGetSQL, OrdersSimpleDto.class);
        nativeQuery.setParameter("memberId", memberId);
        List<OrdersSimpleDto> fetch = nativeQuery.getResultList();

        Set<Long> saleOptionIds = new HashSet<>();
        Set<Long> shopPageIds = new HashSet<>();

        setIds(fetch, saleOptionIds, shopPageIds);
        Map<Long, SaleOptionSimpleDto> saleOptions = getSaleOptions(saleOptionIds);
        Map<Long, ShopPageSimpleDto> shopPages = getShopPages(shopPageIds);

        fetch.forEach(o -> {
            o.setSaleOptionName(saleOptions.get(o.getSaleOptionId()).getName());
            o.setShopPageTitle(shopPages.get(o.getShopPageId()).getTitle());
        });

        return fetch;
    }

    private Map<Long, ShopPageSimpleDto> getShopPages(Set<Long> shopPageIds) {
        Map<Long, ShopPageSimpleDto> shopPages = queryFactory
                .select(ShopPageSimpleDto.asDto())
                .from(shopPage)
                .where(shopPage.id.in(shopPageIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(ShopPageSimpleDto::getId, Function.identity()));
        return shopPages;
    }

    private Map<Long, SaleOptionSimpleDto> getSaleOptions(Set<Long> saleOptionIds) {
        Map<Long, SaleOptionSimpleDto> saleOptions = queryFactory
                .select(SaleOptionSimpleDto.asDto())
                .from(saleOption)
                .where(saleOption.id.in(saleOptionIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(SaleOptionSimpleDto::getId, Function.identity()));
        return saleOptions;
    }

    private void setIds(List<OrdersSimpleDto> fetch, Set<Long> saleOptionIds, Set<Long> shopPageIds) {
        fetch.forEach(o -> {
            saleOptionIds.add(o.getSaleOptionId());
            shopPageIds.add(o.getShopPageId());
        });
    }

    @Override
    public OrdersDetailDto getOrderDetail(Long memberId, Long orderNumber) {
        OrdersDetailDto fetchFirst = queryFactory
                .select(OrdersDetailDto.asDto())
                .from(orders)
                .where(orders.orderNumber.eq(orderNumber).and(orders.memberId.eq(memberId)))
                .fetchFirst();

        if (fetchFirst == null) {
            return null;
        }

        List<SaleOptionSimpleDto> saleOptions = getSaleOptionSimples(fetchFirst);
        List<ShopPageSimpleDto> shopPages = getShopPageSimples(saleOptions);

        fetchFirst.setSaleOptionSimples(saleOptions);
        fetchFirst.setShopPageSimples(shopPages);

        return fetchFirst;
    }

    private List<SaleOptionSimpleDto> getSaleOptionSimples(OrdersDetailDto ordersDto) {
        Set<Long> saleOptionIds = new HashSet<>();
        ordersDto.getOptionQuantities().forEach(oq -> saleOptionIds.add(oq.getSaleOptionId()));

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
