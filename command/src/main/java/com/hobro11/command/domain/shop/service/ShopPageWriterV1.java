package com.hobro11.command.domain.shop.service;

import java.net.URI;

import org.springframework.stereotype.Service;

import com.hobro11.command.core.exception.exceptions.ShopPageNotFoundException;
import com.hobro11.command.core.exception.exceptions.ShopPageUniqueTitleException;
import com.hobro11.command.domain.shop.ShopPage;
import com.hobro11.command.domain.shop.ShopPageStatus;
import com.hobro11.command.domain.shop.service.dto.ShopPageCreateDto;
import com.hobro11.command.domain.shop.service.dto.ShopPageReadOnly;
import com.hobro11.command.infra.ShopPageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopPageWriterV1 implements ShopPageWriter {

    private final ShopPageRepo shopPageRepo;

    @Override
    public ShopPageReadOnly findShopPageReadOnlyById(Long id) {
        return shopPageRepo.findShopPageReadOnlyById(id)
                .orElseThrow(() -> new ShopPageNotFoundException());
    }

    @Override
    public Long createShopPage(ShopPageCreateDto dto) {
        checkUniqueTitle(dto.getTitle());
        ShopPage shopPage = dto.toEntity();
        return shopPageRepo.save(shopPage).getId();
    }

    @Override
    public void checkTitle(String title) {
        checkUniqueTitle(title);
    }

    @Override
    public void updateThumbnailUri(Long id, URI thumbnailUri) {
        ShopPage shopPage = findShopPageById(id);
        shopPage.setThumbnailUri(thumbnailUri);
    }

    @Override
    public void updateDescription(Long id, String description) {
        ShopPage shopPage = findShopPageById(id);
        shopPage.setDescription(description);
    }

    @Override
    public void updateStatus(Long id, ShopPageStatus status) {
        ShopPage shopPage = findShopPageById(id);
        shopPage.setStatus(status);
    }

    @Override
    public void deleteShopPage(Long id) {
        shopPageRepo.deleteById(id);
    }

    private ShopPage findShopPageById(Long id) {
        return shopPageRepo.findById(id)
                .orElseThrow(() -> new ShopPageNotFoundException());
    }

    private void checkUniqueTitle(String title) {
        if (shopPageRepo.existsByTitle(title)) {
            throw new ShopPageUniqueTitleException();
        }
    }
}
