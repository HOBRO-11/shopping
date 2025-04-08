package com.hobro11.command.domain.shop.service;

import com.hobro11.command.domain.shop.service.dto.ShopGeoCreateDto;

public interface ShopGeoWriter {

    void createShopGeo(ShopGeoCreateDto dto);

    void deleteShopGeo(Long shopPageId);

}