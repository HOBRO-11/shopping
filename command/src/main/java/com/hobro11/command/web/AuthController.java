package com.hobro11.command.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hobro11.command.domain.members.service.dto.MemberReadOnly;
import com.hobro11.command.service.AuthService;
import com.hobro11.command.web.form.LoginForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService loginService;

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @ResponseBody
    @PostMapping("/api/auth/login-process")
    public ResponseEntity<String> loginProcess(@Valid @RequestBody LoginForm loginForm) {

        try {
            MemberReadOnly member = loginService.checkLogin(loginForm.getUsername(), loginForm.getPassword());
            ResponseCookie refreshToken = loginService.generateRefreshToken(member);
            ResponseCookie accessToken = loginService.generateAccessToken(member);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, refreshToken.toString())
                    .header(HttpHeaders.SET_COOKIE, accessToken.toString())
                    .build();
        } catch (Exception e) {
            return expireAuthCookies();
        }
    }

    private ResponseEntity<String> expireAuthCookies() {
        ResponseCookie expAccessCookie = loginService.expireAccessToken();
        ResponseCookie expRefreshCookie = loginService.expireRefreshToken();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.SET_COOKIE, expAccessCookie.toString())
                .header(HttpHeaders.SET_COOKIE, expRefreshCookie.toString())
                .build();
    }

    @ResponseBody
    @PostMapping("/api/auth/refresh-token")
    public ResponseEntity<String> refreshToken(@CookieValue("refresh-token") String token) {
        try {
            if (StringUtils.hasText(token)) {
                ResponseCookie refreshToken = loginService.refreshRefreshToken(token);
                ResponseCookie accessToken = loginService.refreshAccessToken(token);

                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.SET_COOKIE, refreshToken.toString())
                        .header(HttpHeaders.SET_COOKIE, accessToken.toString())
                        .build();
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .build();
            }
        } catch (Exception e) {
            return expireAuthCookies();
        }
    }

    @GetMapping("/")
    public String home() {
        return "home.html";
    }


}
