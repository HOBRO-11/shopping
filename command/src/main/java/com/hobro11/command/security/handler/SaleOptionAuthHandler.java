package com.hobro11.command.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hobro11.command.core.exception.exceptions.SaleOptionNotFoundException;
import com.hobro11.command.core.exception.exceptions.ShopPageNotFoundException;
import com.hobro11.command.infra.SaleOptionRepo;
import com.hobro11.command.infra.ShopPageRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaleOptionAuthHandler extends AuthHandler {

    private final SaleOptionRepo repo;
    private final ShopPageRepo shopPageRepo;

    @Override
    protected boolean isAuthor(Long entityId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName().equals(getMemberIdFromEntity(entityId));
    }

    private String getMemberIdFromEntity(Long id) {
        Long shopPageId = repo.findSaleOptionReadOnlyById(id)
                .orElseThrow(() -> new SaleOptionNotFoundException())
                .getShopPageId();
        return shopPageRepo.findShopPageReadOnlyById(shopPageId)
                .orElseThrow(() -> new ShopPageNotFoundException())
                .getMemberId()
                .toString();
    }
    
}
