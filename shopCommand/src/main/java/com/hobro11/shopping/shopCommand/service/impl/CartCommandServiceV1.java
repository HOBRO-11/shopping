package com.hobro11.shopping.shopCommand.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.exception.CartNotFoundException;
import com.hobro11.shopping.orders.service.CartWriter;
import com.hobro11.shopping.orders.service.dto.CartCreateDto;
import com.hobro11.shopping.sales.service.SaleOptionWriter;
import com.hobro11.shopping.sales.service.ShopPageWriter;
import com.hobro11.shopping.sales.service.dto.SaleOptionReadOnly;
import com.hobro11.shopping.shopCommand.service.CartCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartCommandServiceV1 implements CartCommandService {

    private final CartWriter cartWriter;
    private final ShopPageWriter shopPageWriter;
    private final SaleOptionWriter saleOptionWriter;

    @Override
    public void addSaleOptionAtCart(Long memberId, Long saleOptionId, int quantity) {
        Long cartId = 0L;

        try {
            cartId = cartWriter.findCartReadOnlyByMemberId(memberId).getId();
        } catch (CartNotFoundException e) {
            cartId = cartWriter.createCart(new CartCreateDto(memberId));
        }

        SaleOptionReadOnly saleOption = saleOptionWriter.findSaleOptionReadOnlyById(saleOptionId);
        shopPageWriter.findShopPageReadOnlyById(saleOption.getShopPageId());

        cartWriter.addOptionQuantity(cartId, new OptionQuantity(saleOptionId, quantity));
    }

    @Override
    public void removeSaleOptionAtCart(Long memberId, List<Long> saleOptionIds) {
        Long cartId = cartWriter.findCartReadOnlyByMemberId(memberId).getId();
        for (Long id : saleOptionIds) {
            cartWriter.removeOptionQuantity(cartId, new OptionQuantity(id, 0));
        }
    }

    @Override
    public void deleteCartByMemberId(Long memberId) {
        Long cartId = cartWriter.findCartReadOnlyByMemberId(memberId).getId();
        cartWriter.deleteCart(cartId);
    }

}
