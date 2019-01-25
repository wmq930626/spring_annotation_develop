package com.wmq.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/15
 * Time:21:32
 */
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class TestProfileConfig {
    @Value("$user.name")
    private String userName;
    @Value("$user.password")
    private String userPassword;
    @Value("$user.driverclass")
    private String driverClass;
    @Profile("test")
    @Bean
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(userPassword);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }
    @Profile("dev")
    @Bean
    public DataSource dataSourceDev() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(userPassword);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/taobao");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }
    @Profile("Prod")
    @Bean
    public DataSource dataSourceProd() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(userPassword);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/quartz");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }
}
