package data.registry.cars.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Car owners registry",
                version = "1.0.0",
                description = "Car owners database application.")
)
public class OpenApiConfig {

}