package br.com.alelo.consumer.consumerpat.config.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.UpdateCustomerAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.customer.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {
    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(UpdateCustomerAdapter updateCustomerAdapter) {
        return new UpdateCustomerUseCase(updateCustomerAdapter);
    }
}
