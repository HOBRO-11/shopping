package com.hobro11.query.service.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hobro11.query.model.OptionQuantity;
import com.hobro11.query.model.OrdersStatus;
import com.hobro11.query.model.QOrders;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrdersDetailDto {

    private Long orderNumber;
    private Long memberId;
    private Long shopPageId;
    private Set<OptionQuantity> optionQuantities = new HashSet<>();
    private Long price;
    private Long checkSum;
    private OrdersStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Setter
    private List<ShopPageSimpleDto> shopPageSimples;

    @Setter
    private List<SaleOptionSimpleDto> saleOptionSimples;

    public OrdersDetailDto(Long orderNumber, Long memberId, Long shopPageId, Set<OptionQuantity> optionQuantities,
            Long price, Long checkSum, OrdersStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderNumber = orderNumber;
        this.memberId = memberId;
        this.shopPageId = shopPageId;
        this.optionQuantities = optionQuantities;
        this.price = price;
        this.checkSum = checkSum;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Expression<OrdersDetailDto> asDto() {
        QOrders orders = QOrders.orders;
        return Projections.constructor(OrdersDetailDto.class,
                orders.orderNumber,
                orders.memberId,
                orders.shopPageId,
                orders.optionQuantities,
                orders.price,
                orders.checkSum,
                orders.status,
                orders.createdAt,
                orders.updatedAt);
    }

}
