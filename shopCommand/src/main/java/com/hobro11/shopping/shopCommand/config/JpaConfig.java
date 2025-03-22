package com.hobro11.shopping.shopCommand.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
                "com.hobro11.shopping.members",
                "com.hobro11.shopping.orders",
                "com.hobro11.shopping.sales",
                "com.hobro11.shopping.locations"
})
@EntityScan(basePackages = {
                "com.hobro11.shopping.members",
                "com.hobro11.shopping.orders",
                "com.hobro11.shopping.sales",
                "com.hobro11.shopping.locations",
})
public class JpaConfig {

}
