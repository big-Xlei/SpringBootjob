package com.example.demo.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //base，最终调用接口后会和paths拼接在一起
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.web.controller"))
                //过滤的接口
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()));

    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("大熊后台")
                .description("大熊软件科技有限公司，http://www.xl.com")
                .termsOfServiceUrl("http://www.xl.com")
                .version("1.0")
                .build();
    }
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}
