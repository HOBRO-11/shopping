package com.hobro11.shopping.shopQuery.service;

import java.util.List;
import java.util.Optional;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.shopQuery.service.dto.ShopPageDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.ShopPageSimpleDto;

public interface ShopPageQueryService {

    List<ShopPageSimpleDto> getShopPageSimples(List<Long> list, ShopPageStatue status);

    Optional<ShopPageDetailDto> getShopPageDetail(Long shopPageId);

}