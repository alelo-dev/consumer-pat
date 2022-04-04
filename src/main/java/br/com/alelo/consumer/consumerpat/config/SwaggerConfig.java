package br.com.alelo.consumer.consumerpat.config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

		Docket docketASerRetornado = new Docket( DocumentationType.SWAGGER_2 );
		
		return docketASerRetornado.select()
			 	                  .apis(RequestHandlerSelectors.basePackage("br.com.alelo.consumer.consumerpat.controller"))
				                  .paths( PathSelectors.any() )
				                  .build()
				                  .apiInfo( apiInfo() )
				                  .useDefaultResponseMessages(false);
	}

	
	private ApiInfo apiInfo() {
		
	    String  title             = "Consumer CardPAT Project Documentation - By Candidate Erik Lima";
	    String  description       = "Detailed application endpoint list";
        String  version           = "1.0";
        String  termsOfServiceUrl = "https://www.alelo.com.br";
        String  license           = "License - All rights reserved.";
        String  licenseUrl        = "https://www.alelo.com.br";
        Contact contact           = new Contact(" Erik Lima", "https://www.eriklima.com", "erik.f.alves.lima@gmail.com");

        ApiInfo apiInfo = new ApiInfo( 
        		                       title, 
        		                       description, 
        		                       version, 
        		                       termsOfServiceUrl, 
        		                       contact, 
        		                       license, 
        		                       licenseUrl, 
	                                   Collections.emptyList()
	                                  );
	    return apiInfo;	
	}

}