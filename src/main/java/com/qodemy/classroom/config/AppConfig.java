package com.qodemy.classroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

/**
 * @author dmilut
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:db.properties"})
@ComponentScans(value = {@ComponentScan("com.qodemy.classroom.dao"),
        @ComponentScan("com.qodemy.classroom.service")})
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties properties = new Properties();

        // Setting JDBC properties
        properties.put(DRIVER, environment.getProperty("jdbc.driverClassName"));
        properties.put(URL, environment.getProperty("jdbc.url"));
        properties.put(USER, environment.getProperty("jdbc.username"));
        properties.put(PASS, environment.getProperty("jdbc.password"));

        // Setting Hibernate properties
        properties.put(DIALECT, environment.getProperty("hibernate.dialect"));
        properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.put(FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
        properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));

        // Setting C3P0 properties
        properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("com.qodemy.classroom.model");

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());

        return transactionManager;
    }

}