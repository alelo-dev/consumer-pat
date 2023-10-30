package br.com.alelo.consumer.consumerpat.config.card;

import br.com.alelo.consumer.consumerpat.adapters.out.card.RechargeCardAdapter;
import br.com.alelo.consumer.consumerpat.application.core.usecase.card.RechargeCardUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RechargeCardConfig {
    @Bean
    public RechargeCardUseCase rechargeCardUseCase(RechargeCardAdapter rechargeCardAdapter) {
        return new RechargeCardUseCase(rechargeCardAdapter);
    }
}
