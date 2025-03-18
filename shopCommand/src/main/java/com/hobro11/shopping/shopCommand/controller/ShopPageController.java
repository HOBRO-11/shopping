package com.hobro11.shopping.shopCommand.controller;

import java.net.URI;

import org.locationtech.jts.geom.Point;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.shopCommand.controller.form.ShopPageCreateForm;
import com.hobro11.shopping.shopCommand.service.ShopPageCommandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/shopPages")
@RestController
@RequiredArgsConstructor
public class ShopPageController {

    private final ShopPageCommandService shopPageCommandService;

    // TODO: create thumbnailUri, location service 
    @PostMapping
    public Long createShopPage(@Valid @RequestBody ShopPageCreateForm form) {
        URI thumbnailUri = null;
        Point location = null;
        return shopPageCommandService.createShopPage(form.toDto(thumbnailUri, location));
    }

    @PatchMapping("/{shopPageId}/status")
    public void updateStatus(@PathVariable Long shopPageId, @NotNull ShopPageStatue status) {
        shopPageCommandService.updateStatus(shopPageId, status);
    }

    // TODO: create thumbnailUri
    @PatchMapping("/{shopPageId}/thumbnailUri")
    public void updateThumbnailUri(@PathVariable Long shopPageId, @NotNull URI thumbnailUri) {
        shopPageCommandService.updateThumbnailUri(shopPageId, thumbnailUri);
    }

    @PatchMapping("/{shopPageId}/description")
    public void updateDescription(@PathVariable Long shopPageId, @NotNull String description) {
        shopPageCommandService.updateDescription(shopPageId, description);
    }

    @DeleteMapping("/{shopPageId}")
    public void deleteShopPage(@PathVariable Long shopPageId) {
        shopPageCommandService.deleteShopPage(shopPageId);
    }
}
