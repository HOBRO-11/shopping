package com.hobro11.shopping.members.service;

import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.members.service.dto.MemberCreateDto;

public interface MemberWriter {

    void createMember(MemberCreateDto dto);

    void updateName(Long id, String name);

    void updatePhone(Long id, String phone);

    void updateStatus(Long id, MemberStatus status);

}