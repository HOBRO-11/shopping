package com.hobro11.command.domain.members.service;

import org.springframework.stereotype.Service;

import com.hobro11.command.core.exception.exceptions.MemberNotFoundException;
import com.hobro11.command.core.exception.exceptions.MemberUniqueBrnException;
import com.hobro11.command.domain.members.Member;
import com.hobro11.command.domain.members.MemberStatus;
import com.hobro11.command.domain.members.service.dto.MemberCreateDto;
import com.hobro11.command.domain.members.service.dto.MemberReadOnly;
import com.hobro11.command.infra.MemberRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

    private final MemberRepo memberRepo;

    @Override
    public MemberReadOnly findMemberReadOnlyByName(String name) {
        return memberRepo.findMemberReadOnlyByName(name)
                .orElseThrow(() -> new MemberNotFoundException());
    }

    @Override
    public MemberReadOnly findMemberReadOnlyById(Long id) {
        return memberRepo.findMemberReadOnlyById(id)
                .orElseThrow(() -> new MemberNotFoundException());
    }

    @Override
    public Long createMember(MemberCreateDto dto) {
        checkUniqueBrn(dto.getBrn());
        return memberRepo.save(dto.toEntity()).getId();
    }

    @Override
    public void checkBrn(Long brn) {
        checkUniqueBrn(brn);
    }

    private void checkUniqueBrn(Long brn) {
        if(brn == null || brn == 0) {
            return;
        }

        if (memberRepo.existsByBrn(brn)) {
            throw new MemberUniqueBrnException();
        }
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
