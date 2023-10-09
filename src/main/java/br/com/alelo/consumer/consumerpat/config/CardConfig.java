package br.com.alelo.consumer.consumerpat.config;

import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.CardsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfig {


    @Bean
    public Cards cards() {
        return new Cards();
    }

    @Bean
    public Extract extract() {
        return new Extract();
    }

    @Bean
    public Establishment establishment() {
        return new Establishment();
    }

}
