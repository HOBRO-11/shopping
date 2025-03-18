package com.hobro11.shopping.shopCommand.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.members.service.MemberWriter;
import com.hobro11.shopping.members.service.dto.MemberCreateDto;
import com.hobro11.shopping.shopCommand.service.MemberCommandService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceV1 implements MemberCommandService {

    private final MemberWriter memberWriter;

    @Override
    public Long createMember(MemberCreateDto dto) {
        return memberWriter.createMember(dto);
    }

    @Override
    public void checkBrn(Long brn) {
        memberWriter.checkBrn(brn);
    }

    @Override
    public void updateName(Long id, String name) {
        memberWriter.updateName(id, name);
    }

    @Override
    public void updatePhone(Long id, String phone) {
        memberWriter.updatePhone(id, phone);
    }

    @Override
    public void updateStatus(Long id, MemberStatus status) {
        memberWriter.updateStatus(id, status);
    }
}
