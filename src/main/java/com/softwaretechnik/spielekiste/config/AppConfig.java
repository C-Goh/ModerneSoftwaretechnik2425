package com.softwaretechnik.spielekiste.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan(basePackages = "com.softwaretechnik.spielekiste")
public class AppConfig {
}