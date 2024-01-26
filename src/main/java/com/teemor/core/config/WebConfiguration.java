package com.teemor.core.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.teemor.core.security.AuthenticationInterceptor;
import com.teemor.core.security.JwtTokenUtil;
import com.teemor.service.CacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * springmvc web 配置
 *
 * @author lujing
 * @date 2023/12/12
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    @Resource
    private TeeProperties teeProperties;

    @Resource
    private CacheService cacheService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor(jwtTokenUtil(), cacheService);
        authenticationInterceptor.setByPassUrl(teeProperties.getBypassUrl());

        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false)
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance)
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));


    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil(teeProperties.getJwtSecret());
    }


}
