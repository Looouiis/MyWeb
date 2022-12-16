package me.looouiiis.config;

import me.looouiiis.controller.interceptor.ParseInterceptor;
import me.looouiiis.controller.interceptor.UserPermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMVCSupport extends WebMvcConfigurationSupport {
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
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(parseInterceptor).addPathPatterns("/**");
        registry.addInterceptor(userPermissionInterceptor).addPathPatterns("/**");
    }
}
