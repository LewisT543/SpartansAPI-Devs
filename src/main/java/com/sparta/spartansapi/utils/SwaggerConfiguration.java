package com.sparta.spartansapi.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sparta.spartansapi.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Fill out the fields to display desired information which will appear at the top of the documentation
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spartans-API",
                "API which contains Spartan trainee information",
                "",
                "",
                new Contact("", "", ""),
                "", "", Collections.emptyList());
    }
}

//TODO: Add to ReadMe
// http://localhost:8080/swagger-ui/#/
// Might need to run a clean on maven before requesting the above URL