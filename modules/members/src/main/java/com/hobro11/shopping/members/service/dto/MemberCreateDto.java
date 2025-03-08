package com.hobro11.shopping.members.service.dto;

import com.hobro11.shopping.members.Member;
import com.hobro11.shopping.members.MemberRole;
import com.hobro11.shopping.members.MemberStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberCreateDto {

    private final Long brn;
    private final String name;
    private final String phone;
    private final MemberRole role;

    public Member toEntity() {
        return Member.builder()
                .brn(brn)
                .name(name)
                .phone(phone)
                .status(MemberStatus.ACTIVE)
                .role(role)
                .build();
    }
}
