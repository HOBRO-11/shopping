package com.hobro11.command.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hobro11.command.core.jwt.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleTokenValidateFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final String accessTokenName;
    private final String refreshTokenName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;
        for (Cookie c : cookies) {
            if(c.getName().equals(accessTokenName)) {
                token = c.getValue();
            }
        }

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtTokenProvider.validateToken(token, false)) {
            String username = jwtTokenProvider.getUsernameFromToken(token, false);
            String rolesString = jwtTokenProvider.getRoleFromToken(token, false);
            List<SimpleGrantedAuthority> authorities = Collections.emptyList();

            if (rolesString != null && !rolesString.isEmpty()) {
                authorities = Arrays.stream(rolesString.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // 토큰이 유효하지 않은 경우 처리
            expiredAuthTokens(response);
        }

        filterChain.doFilter(request, response);
    }

    private void expiredAuthTokens(HttpServletResponse response) {
        Cookie cookie = new Cookie(accessTokenName, "");
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        cookie = new Cookie(refreshTokenName, "");
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}