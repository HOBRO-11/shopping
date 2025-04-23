package com.hobro11.command.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartAuthHandler extends AuthHandler {

    @Override
    protected boolean isAuthor(Long entityId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName().equals(entityId.toString());
    }
    
}
