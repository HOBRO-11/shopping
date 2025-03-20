package com.hobro11.shopping.locations.service;

import org.springframework.data.util.Pair;

import com.hobro11.shopping.locations.service.dto.ShopGeoCreateDto;

public class GeoHashFuntionV1 implements GeoHashFuntion {

    @Override
    public Pair<Integer, Integer> generateGeoHash(ShopGeoCreateDto dto) {
        Float latitude = dto.getLatitude();
        Float longitude = dto.getLongitude();

        Integer lat = process(latitude, true);
        Integer lon = process(longitude, false);

        return Pair.of(lat, lon);
    }

    private Integer process(Float element, boolean isLat) {
        int coor = (int) (element * 100);

        if (isLat) {
            coor -= 3300;
        } else {
            coor -= 12400;
        }

        return coor / 2;
    }

}
