package br.com.alelo.consumer.consumerpat.config.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.InsertCardCustomerAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.cardcustomer.InsertCardCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCardCustomerConfig {
    @Bean
    public InsertCardCustomerUseCase insertCardCustomerUseCase(InsertCardCustomerAdapter insertCardCustomerAdapter) {
        return new InsertCardCustomerUseCase(insertCardCustomerAdapter);
    }
}
