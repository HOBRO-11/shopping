package com.hobro11.shopping.sales.service.dto;

import java.net.URI;
import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import com.hobro11.shopping.sales.ShopPageStatue;

public interface ShopPageReadOnly {
    Long getId();

    Long getMemberId();

    String getTitle();

    URI getThumbnailUri();

    String getDescription();

    ShopPageStatue getStatus();

    Point getLocation();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
