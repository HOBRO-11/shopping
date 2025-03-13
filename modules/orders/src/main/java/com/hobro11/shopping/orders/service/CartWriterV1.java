package com.hobro11.shopping.orders.service;

import org.springframework.stereotype.Service;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.exception.CartAlreadyExistsException;
import com.hobro11.shopping.orders.exception.CartMaxCountExceededException;
import com.hobro11.shopping.orders.properties.OrdersProperties;
import com.hobro11.shopping.orders.repository.CartRepo;
import com.hobro11.shopping.orders.service.dto.CartCreateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartWriterV1 implements CartWriter {

    private final CartRepo cartRepo;
    private final OrdersProperties ordersProperties;

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
