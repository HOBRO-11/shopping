package com.hobro11.shopping.shopQuery.service.dto;

import com.hobro11.shopping.members.QMember;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSimpleDto {

    private Long id;
    private String name;

    public static Expression<MemberSimpleDto> asDto() {
        QMember member = QMember.member;
        return Projections.constructor(MemberSimpleDto.class,
                member.id,
                member.name);
    }
}
