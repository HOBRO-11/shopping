package com.hobro11.shopping.sales.service.dto;

import java.time.LocalDateTime;

import com.hobro11.shopping.sales.SaleOptionStatus;

public interface SaleOptionReadOnly {

    Long getId();

    String getName();

    Long getShopPageId();

    String getDescription();

    Long getPrice();

    SaleOptionStatus getStatus();

    LocalDateTime getCreatedAt();
}
