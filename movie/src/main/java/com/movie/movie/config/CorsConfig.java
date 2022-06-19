package com.movie.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 当前跨域请求最大有效时长。这里默认1天
//    private static final long MAX_AGE = 24 * 60 * 60;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")//设置允许跨域请求的域名
//                .allowedOrigins("*")
                .allowCredentials(true) //是否允许证书
                .allowedMethods("GET", "POST", "DELETE", "PUT") //允许跨域方法
                .maxAge(3600);//允许跨域时间
    }


}