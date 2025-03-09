package com.hobro11.shopping.orders;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.hobro11.shopping.orders.properties.OrdersProperties;
import com.hobro11.shopping.orders.repository.CartRepo;
import com.hobro11.shopping.orders.repository.OrdersRepo;
import com.hobro11.shopping.orders.service.CartWriter;
import com.hobro11.shopping.orders.service.CartWriterV1;
import com.hobro11.shopping.orders.service.OrdersWriter;
import com.hobro11.shopping.orders.service.OrdersWriterV1;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.hobro11.shopping.orders.repository")
public class OrdersTestConfig {

    @Bean
    public OrdersWriter ordersWriter(OrdersRepo ordersRepo, OrdersProperties ordersProperties) {
        return new OrdersWriterV1(ordersRepo, ordersProperties);
    }

    @Primary
    @Bean
    public OrdersProperties ordersProperties() {
        OrdersProperties ordersProperties = new OrdersProperties();
        // ordersProperties.setSubfixLength(6);
        // ordersProperties.setTryCount(3);
        // ordersProperties.setMaxCount(10);
        return ordersProperties;
    }

    @Bean
    public CartWriter cartWriter(CartRepo cartRepo, OrdersProperties ordersProperties) {
        return new CartWriterV1(cartRepo, ordersProperties);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("rootpassword");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.hobro11.shopping.orders");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("show-sql", "true");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("properties.hibernate.format_sql", "true");

        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
