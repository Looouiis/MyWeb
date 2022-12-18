package me.looouiiis.config;

import jakarta.servlet.Filter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//加载Spring的启动配置
public class InitConfig extends AbstractDispatcherServletInitializer {
    //加载SpringMVC容器配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext webIoC = new AnnotationConfigWebApplicationContext();
        webIoC.register(SpringMVCConfig.class);
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
        AnnotationConfigWebApplicationContext IoC = new AnnotationConfigWebApplicationContext();
        IoC.register(SpringMVCConfig.class);
        return null;
    }
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter character = new CharacterEncodingFilter();
        character.setEncoding("UTF-8");
        return new Filter[]{character};
    }
}
