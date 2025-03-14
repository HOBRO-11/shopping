package com.hobro11.shopping.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.orders.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {


}
