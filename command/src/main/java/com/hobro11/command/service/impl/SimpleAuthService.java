package com.hobro11.command.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.hobro11.command.core.jwt.JwtTokenProvider;
import com.hobro11.command.domain.members.service.MemberService;
import com.hobro11.command.domain.members.service.dto.MemberReadOnly;
import com.hobro11.command.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimpleAuthService implements AuthService {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.access-token.name}")
    private String accessTokenName;

    @Value("${jwt.refresh-token.name}")
    private String refreshTokenName;

    @Value("${jwt.access-token.expiration}")
    private int accessTokenExpiration;

    @Value("${jwt.refresh-token.expiration}")
    private int refreshTokenExpiration;

    @Value("${jwt.access-token.path}")
    private String accessTokenPath;

    @Value("${jwt.refresh-token.path}")
    private String refreshTokenPath;

    @Override
    public MemberReadOnly checkLogin(String username, String password) {
        return memberService.findMemberReadOnlyByName(username);
    }

    @Override
    public ResponseCookie generateAccessToken(MemberReadOnly member) {
        String token = jwtTokenProvider.generateToken(member.getId().toString(), member.getRole().name(), false);
        return getCookie(token, false);
    }

    @Override
    public ResponseCookie generateRefreshToken(MemberReadOnly member) {
        String token = jwtTokenProvider.generateToken(member.getId().toString(), member.getRole().name(), true);
        return getCookie(token, true);
    }

    @Override
    public ResponseCookie refreshRefreshToken(String token) {
        jwtTokenProvider.validateToken(token, true);
        String userName = jwtTokenProvider.getUsernameFromToken(token, true);
        String role = jwtTokenProvider.getRoleFromToken(token, true);
        String newToken = jwtTokenProvider.generateToken(userName, role, true);
        return getCookie(newToken, true);
    }

    @Override
    public ResponseCookie refreshAccessToken(String token) {
        jwtTokenProvider.validateToken(token, false);
        String userName = jwtTokenProvider.getUsernameFromToken(token, false);
        String role = jwtTokenProvider.getRoleFromToken(token, false);
        String newToken = jwtTokenProvider.generateToken(userName, role, false);
        return getCookie(newToken, false);
    }

    private ResponseCookie getCookie(String newToken, boolean isRefreshToken) {
        String name = isRefreshToken ? refreshTokenName : accessTokenName;
        String path = isRefreshToken ? refreshTokenPath : accessTokenPath;
        int expiration = isRefreshToken ? refreshTokenExpiration : accessTokenExpiration;

        return ResponseCookie
                .from(name, newToken)
                .domain("localhost")
                .httpOnly(true)
                .sameSite("Lax")
                .path(path)
                .maxAge(expiration)
                .build();
    }

    @Override
    public ResponseCookie expireRefreshToken() {
        return ResponseCookie.from(refreshTokenName, "").maxAge(0).build();
    }

    @Override
    public ResponseCookie expireAccessToken() {
        return ResponseCookie.from(accessTokenName, "").maxAge(0).build();
    }
}
