package com.hobro11.query.service;

import java.util.Optional;

import com.hobro11.query.service.dto.MemberDetailDto;
import com.hobro11.query.service.dto.MemberSimpleDto;

public interface MemberQueryService {

    Optional<MemberDetailDto> getMemberDetail(Long memberId);

    Optional<MemberSimpleDto> getMemberSimple(Long memberId);

}