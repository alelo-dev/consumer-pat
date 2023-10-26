package br.com.alelo.consumer.consumerpat.config.payments;

import br.com.alelo.consumer.consumerpat.adapters.out.payments.PaymentsAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.payments.PaymentsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Paymentsconfig {
    @Bean
    public PaymentsUseCase paymentsUseCase(PaymentsAdapter paymentsAdapter) {
        return new PaymentsUseCase(paymentsAdapter);
    }
}
