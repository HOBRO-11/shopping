package com.hobro11.command.domain.shop.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.hobro11.command.core.exception.exceptions.ShopGeoAlreadyExistException;
import com.hobro11.command.domain.shop.service.dto.ShopGeoCreateDto;
import com.hobro11.command.infra.ShopGeoRepo;

import lombok.RequiredArgsConstructor;

@Service
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