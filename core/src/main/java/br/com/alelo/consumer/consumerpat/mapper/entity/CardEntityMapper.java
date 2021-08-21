package br.com.alelo.consumer.consumerpat.mapper.entity;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.domain.CardDomain;
import org.springframework.stereotype.Component;

@Component
public class CardEntityMapper {

    public static CardEntity convert(CardDomain domain) {
        if (domain == null) {
            return null;
        }

        return CardEntity.builder()
                .drugstoreCard(domain.getDrugstoreCard())
                .drugstoreCardBalance(domain.getDrugstoreCardBalance())
                .foodCard(domain.getFoodCard())
                .foodCardBalance(domain.getFoodCardBalance())
                .fuelCard(domain.getFuelCard())
                .fuelCardBalance(domain.getFuelCardBalance())
                .build();
    }
}
