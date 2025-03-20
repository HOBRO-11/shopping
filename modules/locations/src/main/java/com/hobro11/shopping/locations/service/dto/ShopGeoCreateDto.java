package com.hobro11.shopping.locations.service.dto;

import org.springframework.data.util.Pair;

import com.hobro11.shopping.locations.GeoHashKey;
import com.hobro11.shopping.locations.ShopGeo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShopGeoCreateDto {

    private final Long shopPageId;
    private final Float latitude;
    private final Float longitude;

    public ShopGeo toEntity(Pair<Integer, Integer> geoHash) {
        return new ShopGeo(shopPageId, latitude, longitude, new GeoHashKey(geoHash.getFirst(), geoHash.getSecond()));
    }
}
