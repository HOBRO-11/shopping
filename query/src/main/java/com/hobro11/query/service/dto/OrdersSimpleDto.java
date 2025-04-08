package com.hobro11.query.service.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.hobro11.query.model.OrdersStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrdersSimpleDto {

    private Long orderNumber;
    private Long memberId;
    private Long shopPageId;
    private Long saleOptionId;
    private Long price;
    private OrdersStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Setter
    private String saleOptionName;
    @Setter
    private String shopPageTitle;

    public OrdersSimpleDto(Long orderNumber, Long memberId, Long shopPageId, Long saleOptionId, Long price,
            String status, Timestamp createdAt, Timestamp updatedAt) {
        this.orderNumber = orderNumber;
        this.memberId = memberId;
        this.shopPageId = shopPageId;
        this.saleOptionId = saleOptionId;
        this.price = price;
        this.status = switch (status) {
            case "IN_PAYMENT" -> OrdersStatus.IN_PAYMENT;
            case "PAID" -> OrdersStatus.PAID;
            case "CANCELED" -> OrdersStatus.CANCELED;
            case "REFUNDED" -> OrdersStatus.REFUNDED;
            default -> throw new IllegalArgumentException("Invalid status: " + status);
        };
        if(createdAt != null) {
            this.createdAt = createdAt.toLocalDateTime();
        }
        if(updatedAt != null) {
            this.updatedAt = updatedAt.toLocalDateTime();
        }
    }

}
