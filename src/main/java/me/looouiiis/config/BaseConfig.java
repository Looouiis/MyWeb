package me.looouiiis.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//SpringMVC的配置文件
@Configuration
@Import({CharSetConfig.class, DruidConfig.class, MyBatisConfig.class})
@ComponentScan({"me.looouiiis.controller","me.looouiiis.service","me.looouiiis.config","me.looouiiis.aop"})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class BaseConfig {
}
