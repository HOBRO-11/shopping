package com.hobro11.command.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.command.domain.members.Member;
import com.hobro11.command.domain.members.service.dto.MemberReadOnly;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Optional<Member> findByBrn(Long brn);

    boolean existsByBrn(Long brn);

    Optional<MemberReadOnly> findMemberReadOnlyById(Long id);

    Optional<MemberReadOnly> findMemberReadOnlyByName(String name);
}
