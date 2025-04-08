package com.hobro11.command.domain.shop.service;

import java.net.URI;

import com.hobro11.command.domain.shop.ShopPageStatus;
import com.hobro11.command.domain.shop.service.dto.ShopPageCreateDto;
import com.hobro11.command.domain.shop.service.dto.ShopPageReadOnly;

public interface ShopPageWriter {

    ShopPageReadOnly findShopPageReadOnlyById(Long id);

    Long createShopPage(ShopPageCreateDto dto);

    void checkTitle(String title);

    void updateThumbnailUri(Long id, URI thumbnailUri);

    void updateDescription(Long id, String description);

    void updateStatus(Long id, ShopPageStatus status);

    void deleteShopPage(Long id);

}