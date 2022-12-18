package me.looouiiis.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//SpringMVC的配置文件
@Configuration
@Import({CharSetConfig.class, SpringMVCSupport.class})
@ComponentScan({"me.looouiiis.controller"})
@EnableAspectJAutoProxy
@EnableWebMvc
public class SpringMVCConfig {
}
