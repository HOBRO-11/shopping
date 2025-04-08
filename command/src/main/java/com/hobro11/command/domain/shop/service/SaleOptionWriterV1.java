
package com.hobro11.command.domain.shop.service;

import org.springframework.stereotype.Service;

import com.hobro11.command.core.exception.exceptions.SaleOptionNotFoundException;
import com.hobro11.command.core.exception.exceptions.SaleOptionUniqueNameException;
import com.hobro11.command.core.exception.exceptions.ShopPageNotFoundException;
import com.hobro11.command.domain.shop.SaleOption;
import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.domain.shop.ShopPage;
import com.hobro11.command.domain.shop.service.dto.SaleOptionCreateDto;
import com.hobro11.command.domain.shop.service.dto.SaleOptionReadOnly;
import com.hobro11.command.infra.SaleOptionRepo;
import com.hobro11.command.infra.ShopPageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleOptionWriterV1 implements SaleOptionWriter {

    private final SaleOptionRepo saleOptionRepo;
    private final ShopPageRepo shopPageRepo;

    @Override
    public SaleOptionReadOnly findSaleOptionReadOnlyById(Long id) {
        return saleOptionRepo.findSaleOptionReadOnlyById(id)
                .orElseThrow(() -> new SaleOptionNotFoundException());
    }

    @Override
    public Long createSaleOption(SaleOptionCreateDto dto) {
        ShopPage shopPage = findShopPageById(dto.getShopPageId());
        checkUniqueName(shopPage, dto.getName());

        SaleOption saleOption = dto.toEntity(shopPage);
        shopPage.addSaleOption(saleOption);

        return saleOptionRepo.save(saleOption).getId();
    }

    private void checkUniqueName(ShopPage shopPage, String name) {
        shopPage.getSaleOptions().stream()
                .filter(so -> so.getName().equals(name)).findFirst()
                .ifPresent(so -> {
                    throw new SaleOptionUniqueNameException();
                });
    }

    @Override
    public void updateStatus(Long id, SaleOptionStatus status) {
        SaleOption saleOption = findSaleOptionById(id);
        saleOption.setStatus(status);
        saleOptionRepo.save(saleOption);
    }

    @Override
    public void deleteSaleOption(Long id) {
        saleOptionRepo.deleteById(id);
    }

    private SaleOption findSaleOptionById(Long id) {
        return saleOptionRepo.findById(id)
                .orElseThrow(() -> new SaleOptionNotFoundException());
    }

    private ShopPage findShopPageById(Long id) {
        return shopPageRepo.findById(id)
                .orElseThrow(() -> new ShopPageNotFoundException());
    }
}
