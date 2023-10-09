package br.com.alelo.consumer.consumerpat.config;

import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Bean
    public ConsumerService consumerService(){
        return new ConsumerService();
    }
}
