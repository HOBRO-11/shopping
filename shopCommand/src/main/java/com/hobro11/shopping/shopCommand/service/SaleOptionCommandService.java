package com.hobro11.shopping.shopCommand.service;

import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;

public interface SaleOptionCommandService {

    Long createSaleOption(SaleOptionCreateDto dto);

    void updateStatus(Long id, SaleOptionStatus status);

}