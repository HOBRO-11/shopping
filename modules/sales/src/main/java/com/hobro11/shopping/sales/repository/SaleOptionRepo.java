package com.hobro11.shopping.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.sales.SaleOption;

@Repository
public interface SaleOptionRepo extends JpaRepository<SaleOption, Long> {
    
}
