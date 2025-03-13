package com.hobro11.shopping.shopQuery.service.dto;

import java.time.LocalDateTime;

import com.hobro11.shopping.members.MemberRole;
import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.members.QMember;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDetailDto {

    private Long id;
    private Long brn;
    private String name;
    private String phone;
    private MemberStatus status;
    private MemberRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Expression<MemberDetailDto> asDto() {
        QMember member = QMember.member;
        return Projections.constructor(MemberDetailDto.class,
                member.id,
                member.brn,
                member.name,
                member.phone,
                member.status,
                member.role,
                member.createdAt,
                member.updatedAt);
    }
}
