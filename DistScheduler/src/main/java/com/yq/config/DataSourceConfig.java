package com.yq.config;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple to Introduction
 * className: DataSourceConfig
 *
 * @author EricYang
 * @version 2018/11/17 17:52
 */
@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.url:jdbc:mysql://127.0.0.1:3306/myscheduler?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false}")
    private String url;

    @Value("${spring.datasource.driver-class-name:com.mysql.jdbc.Driver}")
    private String driverClassName;

    @Value("${spring.datasource.username:user1}")
    private String userName;

    @Value("${spring.datasource.password:password}")
    private String password;

    @Value("${spring.datasource.initialSize:5}")
    private int initialSize;

    @Value("${spring.datasource.minIdle:5}")
    private int minIdle;

    @Value("${pring.datasource.maxActive:20}")
    private int maxActive;

    @Value("${spring.datasource.maxWait:60000}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMilli:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis:300000}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery:SELECT 1 FROM DUAL}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle:true}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow:false}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn:false}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements:true}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.connectionProperties:druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000}")
    private String properties;

    @Bean
    public DataSource dataSource(){
        DruidDataSource configDataSource = new DruidDataSource();
        configDataSource.setUrl(url);
        configDataSource.setDriverClassName(driverClassName);
        configDataSource.setUsername(userName);
        configDataSource.setPassword(password);
        configDataSource.setInitialSize(initialSize);
        configDataSource.setDefaultAutoCommit(true);
        configDataSource.setMinIdle(minIdle);
        configDataSource.setMaxActive(maxActive);
        configDataSource.setMaxWait(maxWait);
        configDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        configDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        configDataSource.setValidationQuery(validationQuery);
        configDataSource.setTestWhileIdle(testWhileIdle);
        configDataSource.setTestOnBorrow(testOnBorrow);
        configDataSource.setTestOnReturn(testOnReturn);
        configDataSource.setConnectionProperties(properties);

        return configDataSource;
    }
}
