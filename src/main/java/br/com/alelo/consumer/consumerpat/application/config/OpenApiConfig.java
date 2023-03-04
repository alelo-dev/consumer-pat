package br.com.alelo.consumer.consumerpat.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        log.info("starting open-api/swagger...");
        return new OpenAPI()
            .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
            .title("Consumer Pat")
            .description("API docs.");
    }

}
