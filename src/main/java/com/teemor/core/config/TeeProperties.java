package com.teemor.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author lujing
 * @since 2024/1/16 14:31
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "teemor")
@Component
@Data
public class TeeProperties {

    private String jwtSecret;

    private Set<String> bypassUrl;


}
