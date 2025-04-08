package com.hobro11.command.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.command.domain.orders.Cart;
import com.hobro11.command.domain.orders.service.dto.CartReadOnly;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    Optional<CartReadOnly> findCartReadOnlyByMemberId(Long memberId);
}
