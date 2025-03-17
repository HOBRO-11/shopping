package com.hobro11.shopping.shopQuery.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EntityScan(basePackages = {
        "com.hobro11.shopping.members",
        "com.hobro11.shopping.orders",
        "com.hobro11.shopping.sales"
})
public class QueryDslConfig {
    
    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
