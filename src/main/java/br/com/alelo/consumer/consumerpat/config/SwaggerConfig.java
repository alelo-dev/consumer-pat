package br.com.alelo.consumer.consumerpat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TITLE        = "Teste Code Review";
    private static final String DESCRIPTION  = "Um teste para vaga Dev Pleno Alelo";
    private static final String VERSION      = "1.0.0";
    private static final String LICENSE      = "Apache License Version 2.0";
    private static final String LICENSE_URL  = "https://www.apache.org/licenses/LICENSE-2.0";
    private static final String NAME_AUTHOR  = "Valdir Cezar";
    private static final String SITE_AUTHO   = "https://www.linkedin.com/in/valdircezar/";
    private static final String EMAIL_AUTHOR = "valdircezar312@gmail.com";
    private static final String BASE_PACKAGE = "br.com.alelo.consumer.consumerpat";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .license(LICENSE)
                .licenseUrl(LICENSE_URL)
                        .contact(new Contact(NAME_AUTHOR, SITE_AUTHO, EMAIL_AUTHOR))
                        .build();
    }

}
