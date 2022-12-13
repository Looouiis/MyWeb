package me.looouiiis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

//SpringMVC的配置文件
@Configuration
@Import({CharSetConfig.class, DruidConfig.class, MyBatisConfig.class})
@ComponentScan({"me.looouiiis.controller","me.looouiiis.service"})
@PropertySource("classpath:jdbc.properties")
public class BaseConfig {
}
