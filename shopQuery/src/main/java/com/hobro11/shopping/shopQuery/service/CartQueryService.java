package com.hobro11.shopping.shopQuery.service;

import com.hobro11.shopping.shopQuery.service.dto.CartDetailDto;

public interface CartQueryService {

    CartDetailDto getCart(Long memberId);

}