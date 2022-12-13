package me.looouiiis.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//加载Spring的启动配置
public class InitConfig extends AbstractDispatcherServletInitializer {
    //加载SpringMVC容器配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext webIoC = new AnnotationConfigWebApplicationContext();
        webIoC.register(BaseConfig.class);
        return webIoC;
    }
    //获取SpringMVC处理的请求
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    //加载Spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
