package br.com.alelo.consumer.consumerpat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TAG= "Consumers";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alelo.consumer.consumerpat"))
                .paths(PathSelectors.any())
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag(TAG, "Esse serviço tem o objetivo de gerenciar os dados e " +
                        "realizar transações de clientes que possuam cartões de benefícios, como vale " +
                        "alimentação, refeição e combustivel."));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Consumer CardPAT").build();
    }
}
