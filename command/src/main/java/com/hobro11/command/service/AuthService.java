package com.hobro11.command.service;

import org.springframework.http.ResponseCookie;

import com.hobro11.command.domain.members.service.dto.MemberReadOnly;

public interface AuthService {
    MemberReadOnly checkLogin(String username, String password);
    ResponseCookie generateRefreshToken(MemberReadOnly member);
    ResponseCookie generateAccessToken(MemberReadOnly member);
    ResponseCookie refreshRefreshToken(String token);
    ResponseCookie refreshAccessToken(String token);
    ResponseCookie expireRefreshToken();
    ResponseCookie expireAccessToken();
}
