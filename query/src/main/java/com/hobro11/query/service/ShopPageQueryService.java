package com.hobro11.query.service;

import java.util.List;
import java.util.Optional;

import com.hobro11.query.model.ShopPageStatus;
import com.hobro11.query.service.dto.ShopPageDetailDto;
import com.hobro11.query.service.dto.ShopPageSimpleDto;

public interface ShopPageQueryService {

    List<ShopPageSimpleDto> getShopPageSimples(List<Long> list, ShopPageStatus status);

    Optional<ShopPageDetailDto> getShopPageDetail(Long shopPageId);

}