package com.qodemy.classroom.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dmilut
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.qodemy.classroom.controller"})
public class WebConfig implements WebMvcConfigurer {
}