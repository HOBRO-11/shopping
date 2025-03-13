package com.hobro11.shopping.members.service.dto;

import java.time.LocalDateTime;

import com.hobro11.shopping.members.MemberRole;
import com.hobro11.shopping.members.MemberStatus;


public interface MemberReadOnly {
    Long getId();
    Long getBrn();
    String getName();
    String getPhone();
    MemberStatus getStatus();
    MemberRole getRole();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
