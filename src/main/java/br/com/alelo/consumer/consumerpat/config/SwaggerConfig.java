package br.com.alelo.consumer.consumerpat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("consumer-controller-1.0")
            .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alelo.consumer.consumerpat.controller.v1"))
                .paths(PathSelectors.any()).build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Consumer API").description(
                        "Documentação de Consumer API v1.0, versão fora das boas práticas, mantida para compatibilidade de possíveis consumidores! " +
                        "Altere a definição para obter a versão 2.0")
                        .build());
    }

    @Bean
    public Docket apiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName("consumer-controller-2.0")
            .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alelo.consumer.consumerpat.controller.v2"))
                .paths(PathSelectors.any()).build()
                .apiInfo(new ApiInfoBuilder().version("2.0").title("Consumer API").description(
                        "Documentação de Consumer API v2.0, construída com base nas práticas do Modelo de Maturidade Richardson.")
                        .build());
    }
}
