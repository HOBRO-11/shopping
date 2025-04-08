package com.hobro11.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.command.domain.shop.ShopGeo;

@Repository
public interface ShopGeoRepo extends JpaRepository<ShopGeo, Long> {

    boolean existsByShopPageId(Long shopPageId);
    void deleteByShopPageId(Long shopPageId);
}
