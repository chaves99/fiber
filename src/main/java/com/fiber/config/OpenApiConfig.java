package com.fiber.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Fiber App Documentation",
                version = "1.0.0",
                contact = @Contact(
                        name = "Vinicius Chaves",
                        email = "viniciusbaleia1999@gmail.com")
        )
)
public class OpenApiConfig {
}
