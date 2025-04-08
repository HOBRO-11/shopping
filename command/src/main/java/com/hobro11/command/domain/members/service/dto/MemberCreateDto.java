package com.hobro11.command.domain.members.service.dto;

import com.hobro11.command.domain.members.Member;
import com.hobro11.command.domain.members.MemberRole;
import com.hobro11.command.domain.members.MemberStatus;

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
