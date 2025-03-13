package com.hobro11.shopping.shopQuery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.hobro11.shopping.members",
        "com.hobro11.shopping.orders",
        "com.hobro11.shopping.sales"
})
public class ShopQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopQueryApplication.class, args);
    }
}
