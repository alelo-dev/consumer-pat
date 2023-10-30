package br.com.alelo.consumer.consumerpat.config.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.FindCardCustomerAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.cardcustomer.FindCustomerCardUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCardCustomerConfig {
    @Bean
    public FindCustomerCardUseCase findCardCustomerUseCase(FindCardCustomerAdapter findCardCustomerAdapter) {
        return new FindCustomerCardUseCase(findCardCustomerAdapter);
    }
}
