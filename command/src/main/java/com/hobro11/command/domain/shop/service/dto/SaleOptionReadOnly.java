package com.hobro11.command.domain.shop.service.dto;

import java.time.LocalDateTime;

import com.hobro11.command.domain.shop.SaleOptionStatus;

public interface SaleOptionReadOnly {

    Long getId();

    String getName();

    Long getShopPageId();

    String getDescription();

    Long getPrice();

    SaleOptionStatus getStatus();

    LocalDateTime getCreatedAt();
}
