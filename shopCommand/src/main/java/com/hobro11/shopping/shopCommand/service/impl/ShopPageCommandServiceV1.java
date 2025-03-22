package com.hobro11.shopping.shopCommand.service.impl;

import java.net.URI;

// import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.sales.service.ShopPageWriter;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;
import com.hobro11.shopping.shopCommand.service.ShopPageCommandService;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class ShopPageCommandServiceV1 implements ShopPageCommandService {

    private final ShopPageWriter shopPageWriter;

    @Override
    public Long createShopPage(ShopPageCreateDto dto) {
        return shopPageWriter.createShopPage(dto);
    }

    @Override
    public void checkTitle(String title) {
        shopPageWriter.checkTitle(title);
    }

    @Override
    public void updateThumbnailUri(Long id, URI thumbnailUri) {
        shopPageWriter.updateThumbnailUri(id, thumbnailUri);
    }

    @Override
    public void updateDescription(Long id, String description) {
        shopPageWriter.updateDescription(id, description);
    }

    @Override
    public void updateStatus(Long id, ShopPageStatue status) {
        shopPageWriter.updateStatus(id, status);
    }

    @Override
    public void deleteShopPage(Long id) {
        shopPageWriter.deleteShopPage(id);
    }
}
