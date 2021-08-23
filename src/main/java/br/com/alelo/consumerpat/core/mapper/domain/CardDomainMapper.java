package br.com.alelo.consumerpat.core.mapper.domain;

import br.com.alelo.consumerpat.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.CardV1RequestDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CardDomainMapper {

    public static Set<CardDomain> convert(Set<CardV1RequestDto> request) {
        if (request == null) {
            return null;
        }

        return request.stream().map(v -> CardDomain.builder()
                        .card(v.getCard())
                        .balance(v.getBalance())
                        .type(v.getType())
                        .build())
                .collect(Collectors.toSet());
    }

    public static CardDomain convert(CardEntity entity) {
        if (entity == null) {
            return null;
        }

        return CardDomain.builder()
                .card(entity.getCardNumber())
                .type(entity.getType())
                .balance(entity.getBalance())
                .build();
    }
}
