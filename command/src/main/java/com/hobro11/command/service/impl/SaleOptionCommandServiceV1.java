package com.hobro11.command.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.service.SaleOptionService;
import com.hobro11.command.domain.shop.service.ShopPageService;
import com.hobro11.command.domain.shop.service.dto.SaleOptionCreateDto;
import com.hobro11.command.service.SaleOptionCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleOptionCommandServiceV1 implements SaleOptionCommandService {
    
    private final SaleOptionService saleOptionService;
    private final ShopPageService shopPageService;

    @Override
    public Long createSaleOption(SaleOptionCreateDto dto) {
        shopPageService.findShopPageReadOnlyById(dto.getShopPageId());
        return saleOptionService.createSaleOption(dto);
    }

    @Override
    public void updateStatus(Long id, SaleOptionStatus status) {
        saleOptionService.updateStatus(id, status);
    }
}
