package com.hobro11.shopping.sales.service.dto;

import java.net.URI;
import java.time.LocalDateTime;

import com.hobro11.shopping.sales.Address;
import com.hobro11.shopping.sales.ShopPageStatue;

public interface ShopPageReadOnly {
    Long getId();

    Long getMemberId();

    String getTitle();

    URI getThumbnailUri();

    String getDescription();

    ShopPageStatue getStatus();

    Address getAddress();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
