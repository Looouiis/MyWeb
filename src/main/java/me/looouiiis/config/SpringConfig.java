package me.looouiiis.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({DruidConfig.class, MyBatisConfig.class})
@ComponentScan({"me.looouiiis.service","me.looouiiis.dao","me.looouiiis.aop"})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {
}
