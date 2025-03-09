package com.hobro11.shopping.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.orders.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
    
}
