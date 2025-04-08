package com.hobro11.command.service;

import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.service.dto.SaleOptionCreateDto;

public interface SaleOptionCommandService {

    Long createSaleOption(SaleOptionCreateDto dto);

    void updateStatus(Long id, SaleOptionStatus status);

}