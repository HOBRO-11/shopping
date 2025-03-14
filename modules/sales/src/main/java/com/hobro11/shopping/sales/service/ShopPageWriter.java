package com.hobro11.shopping.sales.service;

import java.net.URI;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;
import com.hobro11.shopping.sales.service.dto.ShopPageReadOnly;

public interface ShopPageWriter {

    ShopPageReadOnly findShopPageReadOnlyById(Long id);

    Long createShopPage(ShopPageCreateDto dto);

    void checkTitle(String title);

    void updateThumbnailUri(Long id, URI thumbnailUri);

    void updateDescription(Long id, String description);

    void updateStatus(Long id, ShopPageStatue status);

    void deleteShopPage(Long id);

}