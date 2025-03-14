package com.hobro11.shopping.sales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.sales.ShopPage;
import com.hobro11.shopping.sales.service.dto.ShopPageReadOnly;

@Repository
public interface ShopPageRepo extends JpaRepository<ShopPage, Long>{
    
    Optional<ShopPage> findByTitle(String title);

    boolean existsByTitle(String title);

    Optional<ShopPageReadOnly> findShopPageReadOnlyById(Long id);

}
