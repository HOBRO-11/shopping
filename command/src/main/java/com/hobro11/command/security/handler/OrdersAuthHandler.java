package com.hobro11.command.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hobro11.command.core.exception.exceptions.OrdersNotFoundException;
import com.hobro11.command.infra.OrdersRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrdersAuthHandler extends AuthHandler {

    private final OrdersRepo repo;

    @Override
    protected boolean isAuthor(Long entityId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName().equals(getMemberIdFromEntity(entityId));
    }

    private String getMemberIdFromEntity(Long id) {
        return repo.findOrdersReadOnlyByOrderNumber(id)
                .orElseThrow(() -> new OrdersNotFoundException())
                .getMemberId()
                .toString();
    }
    
}
