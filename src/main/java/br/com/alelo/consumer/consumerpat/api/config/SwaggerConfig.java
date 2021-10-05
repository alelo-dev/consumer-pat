package br.com.alelo.consumer.consumerpat.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Teste Alelo")
				.description("Api de testes para desenvolvedores da Alelo")
				.version("V1.0.0")
				.contact(contact())
				.build();
	}
    
    private Contact contact() {
		return new Contact(
				"Rodrigo Bertini", 
				"https://github.com/rodx64", 
				"rodrigo.bertini@gmail.com");
	}
    
	
}
