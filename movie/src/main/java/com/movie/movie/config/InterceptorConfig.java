package com.movie.movie.config;



import com.movie.movie.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> cinemaer = new ArrayList<>();
        cinemaer.add("/CinemaLogin");
        cinemaer.add("/CinemaManage");
        cinemaer.add("/ScheduleList");
        cinemaer.add("/MovieList");
        cinemaer.add("/BuyerList");

        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")// 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                .excludePathPatterns("/user/login","/Login", "/user/addone","/Register")
//                        "/CinemaLogin","/CinemaManage", "/ScheduleList","/MovieList","/BuyerList");
                .excludePathPatterns(cinemaer);
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
