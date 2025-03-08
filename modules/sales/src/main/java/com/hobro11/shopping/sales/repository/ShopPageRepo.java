package com.hobro11.shopping.sales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.sales.ShopPage;

@Repository
public interface ShopPageRepo extends JpaRepository<ShopPage, Long> {
    
    Optional<ShopPage> findByTitle(String title);
}
