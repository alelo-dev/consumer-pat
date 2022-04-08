package br.com.alelo.consumer.consumerpat.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansInitializer {

    @Bean
    public RestOperations getRestOperations() {
        return new RestTemplate();
    }

}
