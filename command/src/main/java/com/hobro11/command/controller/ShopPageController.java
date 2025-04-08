package com.hobro11.command.controller;

import java.net.URI;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.command.controller.form.ShopPageCreateForm;
import com.hobro11.command.domain.shop.ShopPageStatus;
import com.hobro11.command.service.ShopPageCommandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/shopPages")
@RestController
@RequiredArgsConstructor
@Validated
public class ShopPageController {

    private final ShopPageCommandService shopPageCommandService;

    // TODO: create thumbnailUri, location service
    @PostMapping
    public Long createShopPage(@Valid @RequestBody final ShopPageCreateForm form) {
        URI thumbnailUri = null;
        return shopPageCommandService.createShopPage(form.toDto(thumbnailUri));
    }

    @PatchMapping("/{shopPageId}/status")
    public void updateStatus(
            @PathVariable("shopPageId") final Long shopPageId,
            @NotNull @RequestParam("status") final ShopPageStatus status) {
        shopPageCommandService.updateStatus(shopPageId, status);
    }

    // TODO: create thumbnailUri
    @PatchMapping("/{shopPageId}/thumbnailUri")
    public void updateThumbnailUri(
            @PathVariable("shopPageId") final Long shopPageId,
            @NotNull @RequestParam("thumbnailUri") final URI thumbnailUri) {
        shopPageCommandService.updateThumbnailUri(shopPageId, thumbnailUri);
    }

    @PatchMapping("/{shopPageId}/description")
    public void updateDescription(
            @PathVariable("shopPageId") final Long shopPageId,
            @NotNull @RequestParam("description") final String description) {
        shopPageCommandService.updateDescription(shopPageId, description);
    }

    @DeleteMapping("/{shopPageId}")
    public void deleteShopPage(@PathVariable("shopPageId") final Long shopPageId) {
        shopPageCommandService.deleteShopPage(shopPageId);
    }
}
