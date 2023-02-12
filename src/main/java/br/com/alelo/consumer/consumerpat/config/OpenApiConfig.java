package br.com.alelo.consumer.consumerpat.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "basicAuth";
    private static final String SCHEME = "basic";

    @Bean
    public OpenAPI openAPI() {
        log.info("starting open-api/swagger...");
        return new OpenAPI()
            .info(getInfo())
            .components(new Components()
                    .addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
            .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
    }

    private Info getInfo() {
        return new Info()
            .title("Consumer Pat")
            .description("API docs.");
    }
    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

}
