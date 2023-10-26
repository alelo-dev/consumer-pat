package br.com.alelo.consumer.consumerpat.config.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.FindCustomerAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.customer.FindCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCustomerConfig {

    @Bean
    public FindCustomerUseCase listAllCustomerUseCase(FindCustomerAdapter findCustomerAdapter) {
        return new FindCustomerUseCase(findCustomerAdapter);
    }
}
