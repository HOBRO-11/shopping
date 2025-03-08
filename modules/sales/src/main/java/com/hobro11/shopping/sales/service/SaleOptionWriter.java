package com.hobro11.shopping.sales.service;

import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;

public interface SaleOptionWriter {

    Long createSaleOption(SaleOptionCreateDto dto);

    void deleteSaleOption(Long id);

}