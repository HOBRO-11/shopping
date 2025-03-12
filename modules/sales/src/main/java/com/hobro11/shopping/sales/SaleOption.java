package com.hobro11.shopping.sales;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "saleOption")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class SaleOption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShopPage shopPage;

    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    private SaleOptionStatus status;
    
    private Integer price;

    private LocalDateTime createdAt;

    @Builder
    public SaleOption(String name, ShopPage shopPage, String description, SaleOptionStatus status, Integer price) {
        this.name = name;
        this.shopPage = shopPage;
        this.description = description;
        this.status = status;
        this.price = price;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

}
