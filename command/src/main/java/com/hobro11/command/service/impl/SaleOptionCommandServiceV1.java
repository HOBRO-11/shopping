package com.hobro11.command.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.service.SaleOptionWriter;
import com.hobro11.command.domain.shop.service.ShopPageWriter;
import com.hobro11.command.domain.shop.service.dto.SaleOptionCreateDto;
import com.hobro11.command.service.SaleOptionCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleOptionCommandServiceV1 implements SaleOptionCommandService {
    
    private final SaleOptionWriter saleOptionWriter;
    private final ShopPageWriter shopPageWriter;

    @Override
    public Long createSaleOption(SaleOptionCreateDto dto) {
        shopPageWriter.findShopPageReadOnlyById(dto.getShopPageId());
        return saleOptionWriter.createSaleOption(dto);
    }

    @Override
    public void updateStatus(Long id, SaleOptionStatus status) {
        saleOptionWriter.updateStatus(id, status);
    }
}
