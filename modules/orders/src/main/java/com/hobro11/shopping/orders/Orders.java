package com.hobro11.shopping.orders;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Orders {

    @Id
    private Long orderNumber;

    private Long memberId;

    private Long shopPageId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Set<OptionQuantity> optionQuantities = new HashSet<>();

    private Long price;

    @Setter
    private Long checkSum;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrdersStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Orders(Long orderNumber, Long memberId, Long shopPageId, Set<OptionQuantity> optionQuantities, Long price) {
        this.orderNumber = orderNumber;
        this.memberId = memberId;
        this.shopPageId = shopPageId;
        this.optionQuantities = optionQuantities;
        this.price = price;
        this.checkSum = price;
        this.status = OrdersStatus.IN_PAYMENT;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
