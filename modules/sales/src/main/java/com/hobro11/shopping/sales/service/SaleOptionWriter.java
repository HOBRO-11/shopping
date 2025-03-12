package com.hobro11.shopping.sales.service;

import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;

public interface SaleOptionWriter {

    Long createSaleOption(SaleOptionCreateDto dto);

    void updateStatus(Long id, SaleOptionStatus status);

    void deleteSaleOption(Long id);

}