package com.janwes.config;

import com.janwes.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.config
 * @date 2021/9/9 16:06
 * @description 自定义mvc配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册添加拦截器
        registry.addInterceptor(loginInterceptor)
                // 对所有请求进行拦截
                .addPathPatterns("/**")
                // 排除过滤部分请求
                .excludePathPatterns("/user/login", "/index.html");
    }

    /**
     * 拦截请求访问本地资源
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
