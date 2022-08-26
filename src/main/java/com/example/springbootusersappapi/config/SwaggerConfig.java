package com.example.springbootusersappapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getDocket() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2) // Define tipo de documentacion
                .apiInfo(getApiInfo()) // Define informacion de la api
                .select()
                // .apis(RequestHandlerSelectors.any()) // Selecciona todo los requests
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootusersappapi.controllers")) // Selecciona todos los controller en el controlador
                .paths(PathSelectors.any()) // Expone todos los paths
                // .paths(PathSelectors.ant("/users/*")) // Expone los path que cumplen el patron
                .build()
                .useDefaultResponseMessages(false); // Desactiva los mensajes por defecto de swagger
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Users API GM")
                .description("API para gestion de usuarios y roles")
                .version("1.0.0")
                .contact(new Contact("Gustavo Martinez", "", ""))
                .build();
    }
}
