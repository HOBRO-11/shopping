package com.hobro11.command.service;

import com.hobro11.command.domain.members.MemberStatus;
import com.hobro11.command.domain.members.service.dto.MemberCreateDto;

public interface MemberCommandService {

    Long createMember(MemberCreateDto dto);

    void checkBrn(Long brn);

    void updateName(Long id, String name);

    void updatePhone(Long id, String phone);

    void updateStatus(Long id, MemberStatus status);

}