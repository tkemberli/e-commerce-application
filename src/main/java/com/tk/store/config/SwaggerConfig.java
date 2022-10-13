package com.tk.store.config;

import org.apache.naming.ContextAccessController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${api.version}")
    private String apiVersion;

    private Contact contact(){
        return new Contact(
                "tkemberli",
                "https://github.com/tkemberli",
                ""
        );
    }

    private ApiInfoBuilder apiInfoBuilder(){


        return new ApiInfoBuilder()
                .title("Store API")
                .description("A simple e-commerce API")
                .version(this.apiVersion)
                .license("MIT")
                .contact(this.contact());
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tk.store"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfoBuilder().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));
    }
}
