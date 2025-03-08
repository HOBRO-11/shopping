package com.hobro11.shopping.members.service;

import org.springframework.stereotype.Service;

import com.hobro11.shopping.members.Member;
import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.members.exception.MemberNotFoundException;
import com.hobro11.shopping.members.exception.MemberUniqueBrnException;
import com.hobro11.shopping.members.repository.MemberRepo;
import com.hobro11.shopping.members.service.dto.MemberCreateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberWriterV1 implements MemberWriter {

    private final MemberRepo memberRepo;

    @Override
    public void createMember(MemberCreateDto dto) {
        checkUniqueBrn(dto);
        memberRepo.save(dto.toEntity());
    }

    private void checkUniqueBrn(MemberCreateDto dto) {
        memberRepo.findByBrn(dto.getBrn())
                .ifPresent(member -> {
                    throw new MemberUniqueBrnException();
                });
    }

    @Override
    public void updateName(Long id, String name) {
        Member member = findMemberById(id);
        member.setName(name);
    }

    @Override
    public void updatePhone(Long id, String phone) {
        Member member = findMemberById(id);
        member.setPhone(phone);
    }

    @Override
    public void updateStatus(Long id, MemberStatus status) {
        Member member = findMemberById(id);
        member.setStatus(status);
    }

    private Member findMemberById(Long id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new MemberNotFoundException());
    }
}
