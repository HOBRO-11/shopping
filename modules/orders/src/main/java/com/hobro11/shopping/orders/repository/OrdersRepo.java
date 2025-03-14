package com.hobro11.shopping.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.orders.Orders;
import com.hobro11.shopping.orders.service.dto.OrdersReadOnly;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    Optional<OrdersReadOnly> findOrdersReadOnlyByOrderNumber(Long orderNumber);

    boolean existsByOrderNumber(Long orderNumber);

    void deleteByOrderNumber(Long orderNumber);

    Optional<Orders> findByOrderNumber(Long orderNumber);
}
