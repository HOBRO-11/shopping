package com.hobro11.command.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobro11.command.domain.shop.SaleOption;
import com.hobro11.command.domain.shop.service.dto.SaleOptionReadOnly;

@Repository
public interface SaleOptionRepo extends JpaRepository<SaleOption, Long>{

    Optional<SaleOptionReadOnly> findSaleOptionReadOnlyById(Long id);

    
}
