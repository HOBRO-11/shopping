package com.hobro11.shopping.locations.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.hobro11.shopping.locations.repository.ShopGeoRepo;
import com.hobro11.shopping.locations.service.GeoHashFuntion;
import com.hobro11.shopping.locations.service.GeoHashFuntionV1;
import com.hobro11.shopping.locations.service.ShopGeoWriter;
import com.hobro11.shopping.locations.service.ShopGeoWriterV1;
import com.querydsl.jpa.impl.JPAQueryFactory;

@ConditionalOnBean(DataSource.class)
@ConditionalOnMissingBean(JPAQueryFactory.class)
@AutoConfiguration(after = DataSourceAutoConfiguration.class)
public class LocationsAutoConfig {

    @Bean
    public GeoHashFuntion geoHashFuntion() {
        return new GeoHashFuntionV1();
    }
    
    @Bean
    public ShopGeoWriter shopGeoWriter(ShopGeoRepo shopGeoRepo) {
        return new ShopGeoWriterV1(shopGeoRepo, geoHashFuntion());
    }

}
