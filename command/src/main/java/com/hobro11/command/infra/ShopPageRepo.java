package com.hobro11.command.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.command.domain.shop.ShopPage;
import com.hobro11.command.domain.shop.service.dto.ShopPageReadOnly;

@Repository
public interface ShopPageRepo extends JpaRepository<ShopPage, Long>{
    
    Optional<ShopPage> findByTitle(String title);

    boolean existsByTitle(String title);

    Optional<ShopPageReadOnly> findShopPageReadOnlyById(Long id);

}
