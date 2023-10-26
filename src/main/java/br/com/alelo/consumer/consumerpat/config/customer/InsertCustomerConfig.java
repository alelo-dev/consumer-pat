package br.com.alelo.consumer.consumerpat.config.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.InsertCustomerAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.customer.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(InsertCustomerAdapter insertCustomerAdapter) {
        return new InsertCustomerUseCase(insertCustomerAdapter);
    }
}
