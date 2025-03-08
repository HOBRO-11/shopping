package com.hobro11.shopping.members.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.members.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    
    Optional<Member> findByBrn(Long brn);
}
