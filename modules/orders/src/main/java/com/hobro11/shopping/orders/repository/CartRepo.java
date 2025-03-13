package com.hobro11.shopping.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobro11.shopping.orders.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {


}
