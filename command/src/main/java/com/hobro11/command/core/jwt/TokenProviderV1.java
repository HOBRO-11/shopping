package com.hobro11.command.core.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProviderV1 implements JwtTokenProvider {

    @Value("${jwt.refresh-token.secret}")
    private String refreshTokenSecretKey;

    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpTime;

    @Value("${jwt.access-token.secret}")
    private String accessTokenSecretKey;

    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpTime;

    private Key getSignInKey(boolean isRefreshToken) {
        byte[] keyBytes = Decoders.BASE64.decode(isRefreshToken ? refreshTokenSecretKey : accessTokenSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(Authentication authentication, boolean isRefreshToken) {
        String username = authentication.getName();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).findFirst().get();

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
            authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities);

        Date now = new Date();
        Date expiryDate = getExpirationDate(isRefreshToken);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignInKey(isRefreshToken), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateToken(String username, String role, boolean isRefreshToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", role);

        Date now = new Date();
        Date expiryDate = getExpirationDate(isRefreshToken);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignInKey(isRefreshToken), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date getExpirationDate(boolean isRefreshToken) {
        long expireTime = isRefreshToken ? refreshTokenExpTime : accessTokenExpTime;
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireTime * 1000);
        return expiryDate;
    }

    @Override
    public boolean validateToken(String token, boolean isRefreshToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey(isRefreshToken)).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 토큰 검증 실패 시 (만료, 위변조 등)
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token, boolean isRefreshToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey(isRefreshToken)).build().parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public String getRoleFromToken(String token, boolean isRefreshToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey(isRefreshToken)).build().parseClaimsJws(token)
                .getBody();
        return (String) claims.get("roles");
    }

    @Override
    public Date getExpirationDateFromToken(String token, boolean isRefreshToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey(isRefreshToken)).build().parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
}