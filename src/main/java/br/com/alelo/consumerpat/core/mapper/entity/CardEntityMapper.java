package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.domain.CardDomain;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CardEntityMapper {

    public static Set<CardEntity> convert(Set<CardDomain> domain) {
        if (domain == null) {
            return null;
        }

        return domain.stream().map(CardEntityMapper::convert)
                .collect(Collectors.toSet());
    }

    public static CardEntity convert(CardDomain domain) {
        if (domain == null) {
            return null;
        }

        return CardEntity.builder()
                .cardNumber(domain.getCard())
                .balance(domain.getBalance())
                .type(domain.getType())
                .build();
    }
}
