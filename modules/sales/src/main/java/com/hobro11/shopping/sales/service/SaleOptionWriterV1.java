
package com.hobro11.shopping.sales.service;

import com.hobro11.shopping.sales.SaleOption;
import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.ShopPage;
import com.hobro11.shopping.sales.exception.SaleOptionNotFoundException;
import com.hobro11.shopping.sales.exception.SaleOptionUniqueNameException;
import com.hobro11.shopping.sales.exception.ShopPageNotFoundException;
import com.hobro11.shopping.sales.repository.SaleOptionRepo;
import com.hobro11.shopping.sales.repository.ShopPageRepo;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaleOptionWriterV1 implements SaleOptionWriter {

    private final SaleOptionRepo saleOptionRepo;
    private final ShopPageRepo shopPageRepo;

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
        SaleOption saleOption = findSaleOptionById(id);
        saleOptionRepo.delete(saleOption);
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
