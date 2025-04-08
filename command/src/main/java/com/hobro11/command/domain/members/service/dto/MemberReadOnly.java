package com.hobro11.command.domain.members.service.dto;

import java.time.LocalDateTime;

import com.hobro11.command.domain.members.MemberRole;
import com.hobro11.command.domain.members.MemberStatus;

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
