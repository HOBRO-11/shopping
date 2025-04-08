package com.hobro11.query.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ShopQueryProp {

    @Value("${spring.jpa.properties.hibernate.'[default_batch_size]':1000}")
    private int batchSize;

}
