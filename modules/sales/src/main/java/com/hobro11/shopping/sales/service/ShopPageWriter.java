package com.hobro11.shopping.sales.service;

import java.net.URI;

import org.locationtech.jts.geom.Point;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;

public interface ShopPageWriter {

    Long createShopPage(ShopPageCreateDto dto);

    void updateTitle(Long id, String title);

    void updateThumbnailUri(Long id, URI thumbnailUri);

    void updateDescription(Long id, String description);

    void updateStatus(Long id, ShopPageStatue status);

    void updateLocation(Long id, Point location);

    void deleteShopPage(Long id);

}