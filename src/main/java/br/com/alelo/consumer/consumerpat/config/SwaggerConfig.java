package br.com.alelo.consumer.consumerpat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * Configuration for to manipulate instance of Swagger
 *
 * @author mcrj
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACK = "br.com.alelo.consumer.consumerpat.controller";
    private static final String BASE_PATH_CONSUMER = "/consumer.*";
    private static final String BASE_PATH_CARD = "/card.*";

    @Bean
    public Docket apiConsumer() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(basePackage(BASE_PACK))
                .paths(regex(BASE_PATH_CONSUMER).or(regex(BASE_PATH_CARD)))
                .build();
    }
}