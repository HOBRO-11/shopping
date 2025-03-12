package com.hobro11.shopping.sales;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.hobro11.shopping.sales.repository.SaleOptionRepo;
import com.hobro11.shopping.sales.repository.ShopPageRepo;
import com.hobro11.shopping.sales.service.SaleOptionWriter;
import com.hobro11.shopping.sales.service.SaleOptionWriterV1;
import com.hobro11.shopping.sales.service.ShopPageWriter;
import com.hobro11.shopping.sales.service.ShopPageWriterV1;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.hobro11.shopping.sales.repository")
public class SalesPostgreSqlTestConfig {

    @Bean
    public SaleOptionWriter productService(SaleOptionRepo saleOptionRepo, ShopPageRepo shopPageRepo) {
        return new SaleOptionWriterV1(saleOptionRepo, shopPageRepo);
    }

    @Bean
    public ShopPageWriter shopPageWriter(ShopPageRepo shopPageRepo) {
        return new ShopPageWriterV1(shopPageRepo);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.hobro11.shopping.sales");
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
