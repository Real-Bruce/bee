package com.bee.common.config;

import com.bee.common.constant.Constant;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description
 */
@Configuration
@EnableSwagger2WebMvc
@AllArgsConstructor
public class SwaggerConfig {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket creatResApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                // 扫描带ApiOperation注解的类生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build()
                .extensions(openApiExtensionResolver.buildExtensions("bee"))
                .directModelSubstitute(java.util.Date.class, String.class)
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("bee")
                .description("小蜜蜂权限管理系统")
                .termsOfServiceUrl("bee.realpluto.cn")
                .version("0.0.1")
                .contact("Bruce")
                .build();
    }

    private List<ApiKey> security() {
        ApiKey key = new ApiKey(Constant.TOKEN_HEADER, Constant.TOKEN_HEADER, "header");

        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(key);
        return apiKeys;
    }
}
