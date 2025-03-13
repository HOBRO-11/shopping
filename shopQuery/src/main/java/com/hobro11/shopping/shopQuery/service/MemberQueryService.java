package com.hobro11.shopping.shopQuery.service;

import java.util.Optional;

import com.hobro11.shopping.shopQuery.service.dto.MemberDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.MemberSimpleDto;

public interface MemberQueryService {

    Optional<MemberDetailDto> getMemberDetail(Long memberId);

    Optional<MemberSimpleDto> getMemberSimple(Long memberId);

}