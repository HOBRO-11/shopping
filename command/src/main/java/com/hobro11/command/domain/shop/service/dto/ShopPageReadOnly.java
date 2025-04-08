package com.hobro11.command.domain.shop.service.dto;

import java.net.URI;
import java.time.LocalDateTime;

import com.hobro11.command.domain.shop.Address;
import com.hobro11.command.domain.shop.ShopPageStatus;

public interface ShopPageReadOnly {
    Long getId();

    Long getMemberId();

    String getTitle();

    URI getThumbnailUri();

    String getDescription();

    ShopPageStatus getStatus();

    Address getAddress();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
