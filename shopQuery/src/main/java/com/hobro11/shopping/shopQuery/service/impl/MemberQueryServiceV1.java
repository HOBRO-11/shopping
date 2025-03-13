package com.hobro11.shopping.shopQuery.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hobro11.shopping.members.QMember;
import com.hobro11.shopping.shopQuery.service.MemberQueryService;
import com.hobro11.shopping.shopQuery.service.dto.MemberDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.MemberSimpleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceV1 implements MemberQueryService {

    private final JPAQueryFactory queryFactory;
    private QMember member = QMember.member;

    @Override
    public Optional<MemberDetailDto> getMemberDetail(Long memberId) {
        MemberDetailDto findMember = queryFactory
                .select(MemberDetailDto.asDto())
                .from(member)
                .where(member.id.eq(memberId))
                .fetchFirst();

        if (findMember == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(findMember);
    }

    @Override
    public Optional<MemberSimpleDto> getMemberSimple(Long memberId) {
        MemberSimpleDto findMember = queryFactory
                .select(MemberSimpleDto.asDto())
                .from(member)
                .where(member.id.eq(memberId))
                .fetchFirst();

        if (findMember == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(findMember);
    }
}
