package me.looouiiis.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//SpringMVC的配置文件
@Configuration
@Import(CharSetConfig.class)
@ComponentScan({"me.looouiiis.controller","me.looouiiis.config"})
@EnableWebMvc
public class SpringMVCConfig {
}
