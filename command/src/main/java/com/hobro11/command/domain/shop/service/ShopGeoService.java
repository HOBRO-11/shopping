package com.hobro11.command.domain.shop.service;

import com.hobro11.command.domain.shop.service.dto.ShopGeoCreateDto;

public interface ShopGeoService {

    void createShopGeo(ShopGeoCreateDto dto);

    void deleteShopGeo(Long shopPageId);

}