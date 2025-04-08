package com.hobro11.command.domain.shop.service;

import com.hobro11.command.core.exception.exceptions.SaleOptionNotFoundException;
import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.service.dto.SaleOptionCreateDto;
import com.hobro11.command.domain.shop.service.dto.SaleOptionReadOnly;

public interface SaleOptionWriter {

    SaleOptionReadOnly findSaleOptionReadOnlyById(Long id) throws SaleOptionNotFoundException;

    Long createSaleOption(SaleOptionCreateDto dto);

    void updateStatus(Long id, SaleOptionStatus status);

    void deleteSaleOption(Long id);

}