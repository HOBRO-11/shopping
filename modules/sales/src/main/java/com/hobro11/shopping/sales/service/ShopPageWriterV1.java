package com.hobro11.shopping.sales.service;

import java.net.URI;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.sales.ShopPage;
import com.hobro11.shopping.sales.exception.ShopPageNotFoundException;
import com.hobro11.shopping.sales.exception.ShopPageUniqueTitleException;
import com.hobro11.shopping.sales.repository.ShopPageRepo;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopPageWriterV1 implements ShopPageWriter {

    private final ShopPageRepo shopPageRepo;

    @Override
    public Long createShopPage(ShopPageCreateDto dto) {
        checkUniqueTitle(dto.getTitle());
        ShopPage shopPage = dto.toEntity();
        return shopPageRepo.save(shopPage).getId();
    }

    @Override
    public void updateTitle(Long id, String title) {
        ShopPage shopPage = findShopPageById(id);
        shopPage.setTitle(title);
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
    public void updateStatus(Long id, ShopPageStatue status) {
        ShopPage shopPage = findShopPageById(id);
        shopPage.setStatus(status);
    }

    @Override
    public void updateLocation(Long id, Point location) {
        ShopPage shopPage = findShopPageById(id);
        shopPage.setLocation(location);
    }

    @Override
    public void deleteShopPage(Long id) {
        ShopPage shopPage = findShopPageById(id);
        shopPageRepo.delete(shopPage);
    }

    private ShopPage findShopPageById(Long id) {
        return shopPageRepo.findById(id)
                .orElseThrow(() -> new ShopPageNotFoundException());
    }

    private void checkUniqueTitle(String title) {
        shopPageRepo.findByTitle(title)
                .ifPresent(shopPage -> {
                    throw new ShopPageUniqueTitleException();
                });
    }
}
