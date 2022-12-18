package me.looouiiis.config;

import me.looouiiis.controller.interceptor.ParseInterceptor;
import me.looouiiis.controller.interceptor.UserPermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCSupport implements WebMvcConfigurer {
    private UserPermissionInterceptor userPermissionInterceptor;
    private ParseInterceptor parseInterceptor;
    @Autowired
    public void setUserPermissionInterceptor(UserPermissionInterceptor userPermissionInterceptor) {
        this.userPermissionInterceptor = userPermissionInterceptor;
    }
    @Autowired
    public void setParseInterceptor(ParseInterceptor parseInterceptor) {
        this.parseInterceptor = parseInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(parseInterceptor).addPathPatterns("/**");
        registry.addInterceptor(userPermissionInterceptor).addPathPatterns("/**");
    }
}
