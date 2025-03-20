package com.hobro11.shopping.locations.service;

import org.springframework.data.util.Pair;

import com.hobro11.shopping.locations.service.dto.ShopGeoCreateDto;

public interface GeoHashFuntion {

    /**
     * 지도 위 2km^2 격자가 있고 이 격자의 기준점은 33.0000, 124.0000이다.
     * 격자의 좌표를 반환한다.
     */
    Pair<Integer, Integer> generateGeoHash(ShopGeoCreateDto dto);

}