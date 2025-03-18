package com.hobro11.shopping.shopCommand.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.hobro11.shopping.members",
        "com.hobro11.shopping.orders",
        "com.hobro11.shopping.sales",
        "com.hobro11.shopping.shopCommand"
})
public class ApplicationConfig {

}
