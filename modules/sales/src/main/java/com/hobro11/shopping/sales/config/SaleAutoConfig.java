package com.hobro11.shopping.sales.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.hobro11.shopping.sales.repository.SaleOptionRepo;
import com.hobro11.shopping.sales.repository.ShopPageRepo;
import com.hobro11.shopping.sales.service.SaleOptionWriter;
import com.hobro11.shopping.sales.service.SaleOptionWriterV1;
import com.hobro11.shopping.sales.service.ShopPageWriter;
import com.hobro11.shopping.sales.service.ShopPageWriterV1;
import com.querydsl.jpa.impl.JPAQueryFactory;

@ConditionalOnBean({ DataSource.class})
@ConditionalOnMissingBean(JPAQueryFactory.class)
@AutoConfiguration(after = { DataSourceAutoConfiguration.class })
public class SaleAutoConfig {

    @Bean
    public SaleOptionWriter saleOptionWriter(SaleOptionRepo saleOptionRepo, ShopPageRepo shopPageRepo) {
        return new SaleOptionWriterV1(saleOptionRepo, shopPageRepo);
    }

    @Bean
    public ShopPageWriter shopPageWriter(ShopPageRepo shopPageRepo) {
        return new ShopPageWriterV1(shopPageRepo);
    }

}
