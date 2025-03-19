package com.hobro11.shopping.orders.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.hobro11.shopping.orders.properties.OrdersProperties;
import com.hobro11.shopping.orders.repository.CartRepo;
import com.hobro11.shopping.orders.repository.OrdersRepo;
import com.hobro11.shopping.orders.service.CartWriter;
import com.hobro11.shopping.orders.service.CartWriterV1;
import com.hobro11.shopping.orders.service.OrdersWriter;
import com.hobro11.shopping.orders.service.OrdersWriterV1;
import com.querydsl.jpa.impl.JPAQueryFactory;

@ConditionalOnBean(DataSource.class)
@ConditionalOnMissingBean(JPAQueryFactory.class)
@AutoConfiguration(after = {DataSourceAutoConfiguration.class})
public class OrdersAutoConfig {

    @Bean
    public OrdersProperties ordersProperties() {
        System.out.println("ok ordersProperties");
        return new OrdersProperties();
    }
    
    @Bean
    public OrdersWriter ordersWriter(OrdersRepo ordersRepo, OrdersProperties ordersProperties) {
        System.out.println("ok ordersWriter");
        return new OrdersWriterV1(ordersRepo, ordersProperties);
    }

    @Bean
    public CartWriter cartWriter(CartRepo cartRepo, OrdersProperties ordersProperties) {
        System.out.println("ok cartWriter");
        return new CartWriterV1(cartRepo, ordersProperties);
    }

}
