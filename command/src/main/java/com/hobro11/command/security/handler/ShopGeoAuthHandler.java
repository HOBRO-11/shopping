package com.hobro11.command.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ShopGeoAuthHandler extends AuthHandler {

    @Override
    protected boolean isAuthor(Long entityId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName().equals(getMemberIdFromEntity(entityId));
    }

    private String getMemberIdFromEntity(Long id) {
        return id.toString();
    }
    
}
