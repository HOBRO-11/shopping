package com.hobro11.shopping.shopQuery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.sales.ShopPageStatue;
import com.hobro11.shopping.shopQuery.excutable.BatchExcutable;
import com.hobro11.shopping.shopQuery.properties.ShopQueryProp;
import com.hobro11.shopping.shopQuery.service.ShopPageQueryService;
import com.hobro11.shopping.shopQuery.service.dto.ShopPageDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.ShopPageSimpleDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/shopPages")
@RestController
@RequiredArgsConstructor
public class ShopPageController {

    private final ShopPageQueryService shopPageQueryService;
    private final BatchExcutable batchExcutable;
    private final ShopQueryProp prop;

    private int batchSize;

    @PostConstruct
    public void init() {
        batchSize = prop.getBatchSize();
    }

    @GetMapping("/active")
    public List<ShopPageSimpleDto> getActiveShopPageSimples(@RequestBody List<Long> shopPageIds) {
        return batchExcutable.execute(
                batchSize,
                shopPageIds,
                ids -> shopPageQueryService.getShopPageSimples(ids, ShopPageStatue.ACTIVE));
    }

    @GetMapping("/inactive")
    public List<ShopPageSimpleDto> getInactiveShopPageSimples(@RequestBody List<Long> shopPageIds) {
        return batchExcutable.execute(
                batchSize,
                shopPageIds,
                ids -> shopPageQueryService.getShopPageSimples(ids, ShopPageStatue.INACTIVE));
    }

    @GetMapping("/{shopPageId}/detail")
    public ShopPageDetailDto getShopPageDetail(@PathVariable Long shopPageId) {
        return shopPageQueryService.getShopPageDetail(shopPageId).orElse(null);
    }
}
