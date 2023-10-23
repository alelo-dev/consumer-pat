package br.com.alelo.consumer.consumerpat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Log4j2
@Configuration
public class OpenApiConfig {

	private static final Logger log = LogManager.getLogger(OpenApiConfig.class);

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
