package com.hobro11.command.core.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {

    String generateToken(Authentication authentication, boolean isRefreshToken);

    String generateToken(String username, String role, boolean isRefreshToken); // 간단한 생성을 위한 오버로드

    boolean validateToken(String token, boolean isRefreshToken);

    String getUsernameFromToken(String token, boolean isRefreshToken);

    String getRoleFromToken(String token, boolean isRefreshToken); // 권한 정보를 추출하는 메서드 (선택 사항)

    Date getExpirationDateFromToken(String token, boolean isRefreshToken);
}
