package br.com.alelo.consumer.consumerpat.config;

import lombok.Generated;
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
@Generated
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alelo.consumer.consumerpat"))
                .paths(regex("/.*"))
                .build()
                .useDefaultResponseMessages(true)
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("consumer-pat")
                .description("Refatoração de Código para Desafio Alelo")
                .version("v1")
                .contact(this.contact())
                .build();
    }

    private Contact contact() {
        return new Contact(
                "Gustavo Agnes",
                "https://github.com/GustavoAgnes",
                "gustavoagnes94@gmail.com");
    }


}
