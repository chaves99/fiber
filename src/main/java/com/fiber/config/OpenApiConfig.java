package com.fiber.config;

import com.fiber.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Fiber App",
                version = "1.0.0",
                description = "Documentation of endpoint of the Fiber application.",
                contact = @Contact(
                        name = "Vinicius Chaves",
                        email = "viniciusbaleia1999@gmail.com")
        )
)
@SecurityScheme(
        name = OpenApiConstants.SECURITY_SCHEME_NAME,
        description = "The security key of the API is a JWT token that need to be in the header of all request. You can get the token in the /user/login request.",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer"
)
public class OpenApiConfig {
}
