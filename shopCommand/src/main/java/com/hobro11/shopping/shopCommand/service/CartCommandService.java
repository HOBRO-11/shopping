package com.hobro11.shopping.shopCommand.service;

import java.util.List;

public interface CartCommandService {

    void addSaleOptionAtCart(Long memberId, Long saleOptionId, int quantity);

    void removeSaleOptionAtCart(Long memberId, List<Long> saleOptionIds);

    void deleteCartByMemberId(Long memberId);

}