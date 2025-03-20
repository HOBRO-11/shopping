package com.hobro11.shopping.locations.service;

import org.springframework.data.util.Pair;

import com.hobro11.shopping.locations.exception.ShopGeoAlreadyExistException;
import com.hobro11.shopping.locations.repository.ShopGeoRepo;
import com.hobro11.shopping.locations.service.dto.ShopGeoCreateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShopGeoWriterV1 implements ShopGeoWriter {

    private final ShopGeoRepo shopGeoRepo;
    private final GeoHashFuntion geoHashFuntion;

    @Override
    public void createShopGeo(ShopGeoCreateDto dto) {
        if (shopGeoRepo.existsByShopPageId(dto.getShopPageId())) {
            throw new ShopGeoAlreadyExistException();
        }

        Pair<Integer, Integer> geoHash = geoHashFuntion.generateGeoHash(dto);
        shopGeoRepo.save(dto.toEntity(geoHash));
    }

    @Override
    public void deleteShopGeo(Long shopPageId) {
        shopGeoRepo.deleteByShopPageId(shopPageId);
    }
}