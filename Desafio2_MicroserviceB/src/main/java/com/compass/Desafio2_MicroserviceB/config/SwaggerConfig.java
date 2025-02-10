package com.compass.Desafio2_MicroserviceB.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microserviço B API",
                version = "1.0",
                description = "Documentação da API do Microserviço B"
        )
)
public class SwaggerConfig {
}
