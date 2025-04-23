package com.hobro11.command.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hobro11.command.core.exception.exceptions.ShopPageNotFoundException;
import com.hobro11.command.infra.ShopPageRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShopPageAuthHandler extends AuthHandler {

    private final ShopPageRepo repo;

    @Override
    protected boolean isAuthor(Long entityId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName().equals(getMemberIdFromEntity(entityId));
    }

    private String getMemberIdFromEntity(Long id) {
        return repo.findShopPageReadOnlyById(id)
                .orElseThrow(() -> new ShopPageNotFoundException())
                .getMemberId()
                .toString();
    }

}
