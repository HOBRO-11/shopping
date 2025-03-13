package com.hobro11.shopping.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobro11.shopping.orders.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
