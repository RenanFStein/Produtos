package com.produtos.apirest.config;


import com.produtos.apirest.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelSpecification;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {
    // http://127.0.0.1:8080/swagger-ui/index.html#/

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.produtos.apirest"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalRequestParameters(Arrays.asList(
                        new RequestParameterBuilder()
                                .name("Authorization")
                                // Colocado o token na descrição pra facilitar os testes.
                                .description("Ex.:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgZGUgUHJvZHV0b3MiLCJzdWIiOiIyIiwiaWF0IjoxNjY2ODgyOTc3LCJleHAiOjE2NjY5NjkzNzd9.bQ_BFmJgZbTEpKb9j5FYKmOMf2Iuq4jdyx7-e0dEAAg")
                                .required(false)
                                .in("header")
                                .build()));
    }


    }



