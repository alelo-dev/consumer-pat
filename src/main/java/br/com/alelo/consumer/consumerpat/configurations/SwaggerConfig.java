package br.com.alelo.consumer.consumerpat.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alelo.consumer.consumerpat"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Alelo")
                .description("Microservice for consumer transactions")
                .version("1.0.0")
                .contact(new Contact("Paulo Sérgio", "https://www.alelo.com.br/", "paulo.sergio@alelo.com.br"))
                .license("Private stuff, belongs to Alelo")
                .licenseUrl("https://www.alelo.com.br/")
                .build();
    }
}
