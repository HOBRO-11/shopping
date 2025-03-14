package com.hobro11.shopping.sales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.shopping.sales.SaleOption;
import com.hobro11.shopping.sales.service.dto.SaleOptionReadOnly;

@Repository
public interface SaleOptionRepo extends JpaRepository<SaleOption, Long>{

    Optional<SaleOptionReadOnly> findSaleOptionReadOnlyById(Long id);

    
}
