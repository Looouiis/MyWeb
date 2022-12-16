package me.looouiiis.config;

import me.looouiiis.controller.interceptor.UserPermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMVCSupport extends WebMvcConfigurationSupport {
    private UserPermissionInterceptor userPermissionInterceptor;
    @Autowired
    public void setUserPermissionInterceptor(UserPermissionInterceptor userPermissionInterceptor) {
        this.userPermissionInterceptor = userPermissionInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userPermissionInterceptor).addPathPatterns("/manage");
    }
}
