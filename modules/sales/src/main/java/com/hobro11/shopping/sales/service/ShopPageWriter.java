package com.hobro11.shopping.sales.service;

import java.net.URI;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;

public interface ShopPageWriter {

    Long createShopPage(ShopPageCreateDto dto);

    void checkTitle(String title);

    void updateThumbnailUri(Long id, URI thumbnailUri);

    void updateDescription(Long id, String description);

    void updateStatus(Long id, ShopPageStatue status);

    void deleteShopPage(Long id);

}