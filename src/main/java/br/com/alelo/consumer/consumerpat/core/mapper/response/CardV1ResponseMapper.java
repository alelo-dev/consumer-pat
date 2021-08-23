package br.com.alelo.consumer.consumerpat.core.mapper.response;

import br.com.alelo.consumer.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.response.CardV1ResponseDto;

import java.util.Set;
import java.util.stream.Collectors;

public class CardV1ResponseMapper {

    public static Set<CardV1ResponseDto> convert(Set<CardDomain> cardDomains) {
        if (cardDomains == null) {
            return null;
        }

        return cardDomains.stream().map(v ->
                CardV1ResponseDto.builder()
                        .cardNumber(v.getCard())
                        .balance(v.getBalance())
                        .type(v.getType())
                        .build()
        ).collect(Collectors.toSet());
    }
}
