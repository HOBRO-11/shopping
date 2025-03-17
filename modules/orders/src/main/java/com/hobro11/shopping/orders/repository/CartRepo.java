package com.hobro11.shopping.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.orders.Cart;
import com.hobro11.shopping.orders.service.dto.CartReadOnly;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    Optional<CartReadOnly> findCartReadOnlyByMemberId(Long memberId);
}
