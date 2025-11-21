package com.cfloresh.springboot.app.cinemarest;

import com.cfloresh.springboot.app.cinemarest.config.CinemaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(CinemaProperties.class)
@PropertySource("classpath:cinema.properties")
public class AppConfig {
}
