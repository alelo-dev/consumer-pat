package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.v1.response.CardV1ResponseDto;

import java.util.Set;
import java.util.stream.Collectors;

public class CardV1ResponseMapper {

    public static Set<CardV1ResponseDto> convert(Set<CardEntity> cardEntity) {
        if (cardEntity == null) {
            return null;
        }

        return cardEntity.stream().map(v ->
                CardV1ResponseDto.builder()
                        .cardNumber(v.getCardNumber())
                        .balance(v.getBalance())
                        .type(v.getType())
                        .build()
        ).collect(Collectors.toSet());
    }
}
