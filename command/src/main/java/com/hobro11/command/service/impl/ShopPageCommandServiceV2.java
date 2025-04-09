package com.hobro11.command.service.impl;

import java.net.URI;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hobro11.command.core.client.Coordinate;
import com.hobro11.command.core.client.GeoDataClient;
import com.hobro11.command.domain.shop.ShopPageStatus;
import com.hobro11.command.domain.shop.service.ShopGeoWriter;
import com.hobro11.command.domain.shop.service.ShopPageWriter;
import com.hobro11.command.domain.shop.service.dto.ShopGeoCreateDto;
import com.hobro11.command.domain.shop.service.dto.ShopPageCreateDto;
import com.hobro11.command.service.ShopPageCommandService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopPageCommandServiceV2 implements ShopPageCommandService {

    private final ShopPageWriter shopPageWriter;
    private final ShopGeoWriter shopGeoWriter;
    private final GeoDataClient geoDataProvider;

    @Override
    public Long createShopPage(ShopPageCreateDto dto) {
        Coordinate coordinate = getCoordinate(dto);

        Long shopPageId = shopPageWriter.createShopPage(dto);
        ShopGeoCreateDto geoDto = getGeoDto(coordinate, shopPageId);
        shopGeoWriter.createShopGeo(geoDto);
        return shopPageId;
    }

    private ShopGeoCreateDto getGeoDto(Coordinate coordinate, Long shopPageId) {
        return new ShopGeoCreateDto(shopPageId, coordinate.getLatitude(), coordinate.getLongitude());
    }

    private Coordinate getCoordinate(ShopPageCreateDto dto) {
        String addressName = dto.getAddress().getAddressName();
        Integer zoneNo = dto.getZoneNo();

        Map<Integer, Coordinate> candidates = geoDataProvider.getCandidates(addressName, Coordinate.class);
        Coordinate coordinate = candidates.get(zoneNo);

        if (coordinate == null) {
            throw new IllegalArgumentException("Invalid zoneNo");
        }

        return coordinate;
    }

    @Override
    public void checkTitle(String title) {
        shopPageWriter.checkTitle(title);
    }

    @Override
    public void updateThumbnailUri(Long id, URI thumbnailUri) {
        shopPageWriter.updateThumbnailUri(id, thumbnailUri);
    }

    @Override
    public void updateDescription(Long id, String description) {
        shopPageWriter.updateDescription(id, description);
    }

    @Override
    public void updateStatus(Long id, ShopPageStatus status) {
        shopPageWriter.updateStatus(id, status);
    }

    @Override
    public void deleteShopPage(Long id) {
        shopPageWriter.deleteShopPage(id);
    }
}
