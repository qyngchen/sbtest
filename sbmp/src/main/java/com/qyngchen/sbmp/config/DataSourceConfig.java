/*
 * Copyright (c) 2019-2020, Beyondsoft All rights reserved..
 * <p>
 * File Name		:  DataSourceConfig.java
 * Author			:  v-tiqiu
 * Version			:  V1.0
 * CreateDate		:  2019/05/14
 * EditDate			:  2019/05/14
 * Description		:  数据源配置
 */
package com.qyngchen.sbmp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 数据源初始化，包括获取数据库配置信息，创建SQL session工厂
 */
@Configuration
public class DataSourceConfig {

    /**
     * 日志记录对象
     */
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 数据库访问Url
     */
    @Value("${datasource.url}")
    private String url;

    /**
     * 数据库访问用户名
     */
    @Value("${datasource.username}")
    private String userName;

    /**
     * 数据访问密码
     */
    @Value("${datasource.password}")
    private String password;

    /**
     * 获取数据源配置
     *
     * @return
     */
    @Bean(name = "dbDataSource")
    public DataSource getDataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl(url);
        source.setUsername(userName);
        source.setPassword(password);
        return source;
    }

    /**
     * 数据库SQL session 工厂，初始化数据库连接
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dbDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
            sqlSessionFactoryBean.setDataSource(dataSource);
            return sqlSessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("Filed to connection DB,url:{},userName:{}", url, userName, e);
            return null;
        } catch (Exception e) {
            logger.error("Filed to connection DB,url:{},userName:{}", url, userName, e);
            return null;
        }
    }

    /**
     * 初始化数据库SQL session 模板
     *
     * @param factory
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory factory) {
        SqlSessionTemplate template = new SqlSessionTemplate(factory);
        return template;
    }

}
