package br.com.alelo.consumer.consumerpat.mapper.domain;

import br.com.alelo.consumer.consumerpat.domain.CardDomain;
import br.com.alelo.consumer.consumerpat.dto.v1.request.CardV1RequestDto;
import org.springframework.stereotype.Component;

@Component
public class CardDomainMapper {

    public static CardDomain convert(CardV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return CardDomain.builder()
                .drugstoreCard(request.getDrugstoreCard())
                .drugstoreCardBalance(request.getDrugstoreCardBalance())
                .foodCard(request.getFoodCard())
                .foodCardBalance(request.getFoodCardBalance())
                .fuelCard(request.getFuelCard())
                .fuelCardBalance(request.getFuelCardBalance())
                .build();
    }
}
