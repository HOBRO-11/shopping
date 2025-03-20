package com.hobro11.shopping.locations.service;

import com.hobro11.shopping.locations.service.dto.ShopGeoCreateDto;

public interface ShopGeoWriter {

    void createShopGeo(ShopGeoCreateDto dto);

    void deleteShopGeo(Long shopPageId);

}