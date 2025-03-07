package com.hobro11.shopping.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.product.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

}
