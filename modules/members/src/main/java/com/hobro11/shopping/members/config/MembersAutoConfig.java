package com.hobro11.shopping.members.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.hobro11.shopping.members.repository.MemberRepo;
import com.hobro11.shopping.members.service.MemberWriter;
import com.hobro11.shopping.members.service.MemberWriterV1;
import com.querydsl.jpa.impl.JPAQueryFactory;

@ConditionalOnBean(DataSource.class)
@ConditionalOnMissingBean(JPAQueryFactory.class)
@AutoConfiguration(after = DataSourceAutoConfiguration.class)
public class MembersAutoConfig {

    @Bean
    public MemberWriter memberWriter(MemberRepo memberRepo) {
        System.out.println("ok memberWriter");
        return new MemberWriterV1(memberRepo);
    }
}
