package com.hobro11.command.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.command.domain.members.MemberStatus;
import com.hobro11.command.domain.members.service.MemberService;
import com.hobro11.command.domain.members.service.dto.MemberCreateDto;
import com.hobro11.command.service.MemberCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceV1 implements MemberCommandService {

    private final MemberService memberService;

    @Override
    public Long createMember(MemberCreateDto dto) {
        return memberService.createMember(dto);
    }

    @Override
    public void checkBrn(Long brn) {
        memberService.checkBrn(brn);
    }

    @Override
    public void updateName(Long id, String name) {
        memberService.updateName(id, name);
    }

    @Override
    public void updatePhone(Long id, String phone) {
        memberService.updatePhone(id, phone);
    }

    @Override
    public void updateStatus(Long id, MemberStatus status) {
        memberService.updateStatus(id, status);
    }
}
