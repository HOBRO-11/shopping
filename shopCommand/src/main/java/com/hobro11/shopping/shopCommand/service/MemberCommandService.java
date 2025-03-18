package com.hobro11.shopping.shopCommand.service;

import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.members.service.dto.MemberCreateDto;

public interface MemberCommandService {

    Long createMember(MemberCreateDto dto);

    void checkBrn(Long brn);

    void updateName(Long id, String name);

    void updatePhone(Long id, String phone);

    void updateStatus(Long id, MemberStatus status);

}