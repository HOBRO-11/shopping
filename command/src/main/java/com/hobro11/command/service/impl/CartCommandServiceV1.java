package com.hobro11.command.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.command.domain.orders.OptionQuantity;
import com.hobro11.command.core.exception.exceptions.CartNotFoundException;
import com.hobro11.command.domain.orders.service.CartService;
import com.hobro11.command.domain.orders.service.dto.CartCreateDto;
import com.hobro11.command.domain.shop.service.SaleOptionService;
import com.hobro11.command.domain.shop.service.ShopPageService;
import com.hobro11.command.domain.shop.service.dto.SaleOptionReadOnly;
import com.hobro11.command.service.CartCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartCommandServiceV1 implements CartCommandService {

    private final CartService cartService;
    private final ShopPageService shopPageService;
    private final SaleOptionService saleOptionService;

    @Override
    public void addSaleOptionAtCart(Long memberId, Long saleOptionId, int quantity) {
        Long cartId = 0L;

        try {
            cartId = cartService.findCartReadOnlyByMemberId(memberId).getId();
        } catch (CartNotFoundException e) {
            cartId = cartService.createCart(new CartCreateDto(memberId));
        }

        SaleOptionReadOnly saleOption = saleOptionService.findSaleOptionReadOnlyById(saleOptionId);
        shopPageService.findShopPageReadOnlyById(saleOption.getShopPageId());

        cartService.addOptionQuantity(cartId, new OptionQuantity(saleOptionId, quantity));
    }

    @Override
    public void removeSaleOptionAtCart(Long memberId, List<Long> saleOptionIds) {
        Long cartId = cartService.findCartReadOnlyByMemberId(memberId).getId();
        for (Long id : saleOptionIds) {
            cartService.removeOptionQuantity(cartId, new OptionQuantity(id, 0));
        }
    }

    @Override
    public void deleteCartByMemberId(Long memberId) {
        Long cartId = cartService.findCartReadOnlyByMemberId(memberId).getId();
        cartService.deleteCart(cartId);
    }

}
