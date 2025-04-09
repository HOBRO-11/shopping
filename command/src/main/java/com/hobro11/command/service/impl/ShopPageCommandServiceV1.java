package com.hobro11.command.service.impl;

import java.net.URI;

// import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.command.domain.shop.ShopPageStatus;
import com.hobro11.command.domain.shop.service.ShopPageService;
import com.hobro11.command.domain.shop.service.dto.ShopPageCreateDto;
import com.hobro11.command.service.ShopPageCommandService;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class ShopPageCommandServiceV1 implements ShopPageCommandService {

    private final ShopPageService shopPageService;

    @Override
    public Long createShopPage(ShopPageCreateDto dto) {
        return shopPageService.createShopPage(dto);
    }

    @Override
    public void checkTitle(String title) {
        shopPageService.checkTitle(title);
    }

    @Override
    public void updateThumbnailUri(Long id, URI thumbnailUri) {
        shopPageService.updateThumbnailUri(id, thumbnailUri);
    }

    @Override
    public void updateDescription(Long id, String description) {
        shopPageService.updateDescription(id, description);
    }

    @Override
    public void updateStatus(Long id, ShopPageStatus status) {
        shopPageService.updateStatus(id, status);
    }

    @Override
    public void deleteShopPage(Long id) {
        shopPageService.deleteShopPage(id);
    }
}
