package com.teemor.core.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 * 分组 admin  api
 *
 * @author lujing
 * @since 2023/12/12 17:53
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "adminDockerBean")
    public Docket adminDockerBean() {

        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, In.HEADER.toValue()));
        List<Parameter> parameterList = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(new ApiInfoBuilder()
                        .title("后台管理系统")
                        .description("## RESTful APIs")
                        .termsOfServiceUrl("")
                        .version("1.0")
                        .build())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("^/(api/v1/common/.*|api/v1/admin/.*)$"))
                .build()
                .globalOperationParameters(parameterList)
                .securitySchemes(apiKeyList);
    }

    @Bean(value = "apiDockerBean")
    public Docket apiDockerBean() {

        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, In.HEADER.toValue()));
        List<Parameter> parameterList = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(new ApiInfoBuilder()
                        .title("APP API")
                        .description("## RESTful APIs")
                        .termsOfServiceUrl("")
                        .version("1.0")
                        .build())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("^/(api/v1/common/.*|api/v1/app/.*)$"))
                .build()
                .globalOperationParameters(parameterList)
                .securitySchemes(apiKeyList);

    }
}
