package com.wany.myuestcbbs.config;

import com.wany.myuestcbbs.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new JwtInterceptor())
                //拦截的路径 需要进行token验证的路径
                .addPathPatterns("/v1/**")
                //放行的路径 登录 注册 swagger等等
                .excludePathPatterns("/v1/accounts/login/**", "/v1/accounts/register/**", "/swagger-ui.html/**", "/swagger-resources/**")
                .excludePathPatterns("/v1/files/**");
    }
}
