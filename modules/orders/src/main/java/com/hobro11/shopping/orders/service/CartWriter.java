package com.hobro11.shopping.orders.service;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.service.dto.CartCreateDto;
import com.hobro11.shopping.orders.service.dto.CartReadOnly;

public interface CartWriter {

    CartReadOnly findCartReadOnlyByMemberId(Long memberId);

    Long createCart(CartCreateDto dto);

    void addOptionQuantity(Long cartId, OptionQuantity optionQuantity);

    void removeOptionQuantity(Long cartId, OptionQuantity optionQuantity);

    void updateOptionQuantity(Long cartId, Long saleOptionId, Integer quantity);

    void deleteCart(Long cartId);

}