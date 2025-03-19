package com.hobro11.shopping.orders.properties;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersProperties {
    
    @Value("${orders.cart.max_count:10}")
    private int maxCount;

    @Value("${orders.orders.date_format:yyMMdd}")
    private String dateFormat;

    @Value("${orders.orders.subfix_length:6}")
    private int subfixLength;

    @Value("${orders.orders.try_count:3}")
    private int tryCount;
}
