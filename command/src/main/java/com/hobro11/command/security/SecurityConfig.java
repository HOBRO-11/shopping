package com.hobro11.command.security;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ORIGIN;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobro11.command.core.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true) 
@RequiredArgsConstructor
public class SecurityConfig {

        private final ObjectMapper objectMapper;
        private static final String APPLICATION_JSON = "application/json";
        private static final Map<String, String> ERROR_MSG = Map.of("message", "접근이 제한되었습니다.");
        private final JwtTokenProvider jwtTokenProvider;
        @Value("${jwt.access-token.name}")
        private String accessTokenName;
        @Value("${jwt.refresh-token.name}")
        private String refreshTokenName;

        @Bean
        public SimpleTokenValidateFilter simpleTokenValidateFilter() {
                return new SimpleTokenValidateFilter(jwtTokenProvider, accessTokenName, refreshTokenName);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                String[] allowPaths = { "/login.html", "/signup", "/error/**", "/", "/home.html", "/login",
                                "/api/auth/**",
                                "/favicon.ico" };

                http
                                .csrf(CsrfConfigurer::disable)
                                .httpBasic(AbstractHttpConfigurer::disable)
                                .formLogin(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                                .requestMatchers(allowPaths).permitAll()
                                                .anyRequest().authenticated())
                                .addFilterBefore(simpleTokenValidateFilter(),
                                                UsernamePasswordAuthenticationFilter.class)
                                .logout((logout) -> logout
                                                .logoutSuccessUrl("/")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID", "Authentication"))
                                .exceptionHandling(
                                                (exception) -> exception
                                                                .accessDeniedHandler((request, response,
                                                                                accessDeniedException) -> accessDeniedHandler(
                                                                                                request, response,
                                                                                                accessDeniedException))
                                                                .authenticationEntryPoint((request, response,
                                                                                authException) -> authenticationEntryPoint(
                                                                                                request, response,
                                                                                                authException)))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .cors(cors -> cors
                                                .configurationSource(apiCorsConfigurationSource()));
                return http.build();

        }

        @Bean
        public UrlBasedCorsConfigurationSource apiCorsConfigurationSource() {

                List<String> allowedOrigins = List.of("http://localhost:8070");
                List<String> allowedHeaders = List.of(ACCEPT, AUTHORIZATION, ACCEPT_LANGUAGE, CONTENT_TYPE, ORIGIN,
                                "X-Requested-With");
                List<String> allowedMethods = List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS");

                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(allowedOrigins);
                config.setAllowedHeaders(allowedHeaders);
                config.setAllowedMethods(allowedMethods);
                config.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);
                return source;
        }

        @SuppressWarnings("unused")
        private void accessDeniedHandler(HttpServletRequest request, HttpServletResponse response,
                        AccessDeniedException accessDeniedException) {
                try {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType(APPLICATION_JSON);
                        response.getWriter().write(objectMapper.writeValueAsString(ERROR_MSG));
                } catch (IOException e) {
                }
        }

        @SuppressWarnings("unused")
        private void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) {
                try {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType(APPLICATION_JSON);
                        response.getWriter().write(objectMapper.writeValueAsString(ERROR_MSG));
                } catch (IOException e) {
                }
        }
}
