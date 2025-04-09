package com.hobro11.command.domain.orders.service;

import org.springframework.stereotype.Service;

import com.hobro11.command.core.exception.exceptions.CartAlreadyExistsException;
import com.hobro11.command.core.exception.exceptions.CartMaxCountExceededException;
import com.hobro11.command.core.exception.exceptions.CartNotFoundException;
import com.hobro11.command.domain.orders.OptionQuantity;
import com.hobro11.command.domain.orders.properties.OrdersProperties;
import com.hobro11.command.domain.orders.service.dto.CartCreateDto;
import com.hobro11.command.domain.orders.service.dto.CartReadOnly;
import com.hobro11.command.infra.CartRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceV1 implements CartService {

    private final CartRepo cartRepo;
    private final OrdersProperties ordersProperties;

    @Override
    public CartReadOnly findCartReadOnlyByMemberId(Long memberId) {
        return cartRepo.findCartReadOnlyByMemberId(memberId)
                .orElseThrow(() -> new CartNotFoundException());
    }

    @Override
    public Long createCart(CartCreateDto dto) {
        Long memberId = dto.getMemberId();
        cartRepo.findById(memberId).ifPresent(cart -> {
            throw new CartAlreadyExistsException();
        });
        return cartRepo.save(dto.toEntity()).getId();
    }

    @Override
    public void addOptionQuantity(Long cartId, OptionQuantity optionQuantity) {
        int maxCount = ordersProperties.getMaxCount();
        cartRepo.findById(cartId).ifPresent(cart -> {
            if (cart.getOptionQuantities().size() >= maxCount) {
                throw new CartMaxCountExceededException();
            }

            cart.addOptionQuantity(optionQuantity);
            cartRepo.save(cart);
        });
    }

    @Override
    public void removeOptionQuantity(Long cartId, OptionQuantity optionQuantity) {
        cartRepo.findById(cartId).ifPresent(cart -> {
            cart.removeOptionQuantity(optionQuantity);
            cartRepo.save(cart);
        });
    }

    @Override
    public void updateOptionQuantity(Long cartId, Long saleOptionId, Integer quantity) {
        cartRepo.findById(cartId).ifPresent(cart -> {
            cart.removeOptionQuantity(new OptionQuantity(saleOptionId, quantity));
            cart.addOptionQuantity(new OptionQuantity(saleOptionId, quantity));
            cartRepo.save(cart);
        });
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepo.deleteById(cartId);
    }

}
