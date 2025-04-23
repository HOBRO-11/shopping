package com.hobro11.command.security.handler;

public abstract class AuthHandler {
    public final boolean check(Long entityId) {
        return isAuthor(entityId);
    }

    abstract protected boolean isAuthor(Long entityId);
}
