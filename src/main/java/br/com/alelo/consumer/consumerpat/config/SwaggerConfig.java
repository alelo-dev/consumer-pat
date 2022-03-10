package br.com.alelo.consumer.consumerpat.config;

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
        .paths(regex("/.*"))
        .build()
        .useDefaultResponseMessages(true)
        .apiInfo(getInfo());
  }

  private ApiInfo getInfo() {
    return new ApiInfoBuilder()
        .title("consumer-pat-api")
        .description(
            "Esse serviço tem o objetivo de gerenciar os dados e realizar transações de clientes que possuam cartões de benefícios, como vale alimentação, refeição e combustivel.")
        .version("v1")
        .contact(
            new Contact(
                "Rodrigo Norões Moura de Carvalho",
                "https://www.linkedin.com/in/rodrigonmoura/",
                "rodrigo.noroes@gmail.com"))
        .build();
  }
}
