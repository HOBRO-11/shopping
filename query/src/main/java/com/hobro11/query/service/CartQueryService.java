package com.hobro11.query.service;

import com.hobro11.query.service.dto.CartDetailDto;

public interface CartQueryService {

    CartDetailDto getCart(Long memberId);

}