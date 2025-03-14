package com.hobro11.shopping.sales.service;

import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.exception.SaleOptionNotFoundException;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;
import com.hobro11.shopping.sales.service.dto.SaleOptionReadOnly;

public interface SaleOptionWriter {

    SaleOptionReadOnly findSaleOptionReadOnlyById(Long id) throws SaleOptionNotFoundException;

    Long createSaleOption(SaleOptionCreateDto dto);

    void updateStatus(Long id, SaleOptionStatus status);

    void deleteSaleOption(Long id);

}