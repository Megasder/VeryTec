package com.example.VeryTec.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class VeryTecAppConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("VeryTecApp")
                        .description("")
                        .contact(new Contact()
                                .name("VeryTec")
                                .email("verytecapp@gmail.com")
                                .url("https://verytec.com"))
                        .version("1.0"));
    }
}
