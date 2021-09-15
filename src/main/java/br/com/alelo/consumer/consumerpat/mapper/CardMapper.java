package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.response.CardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toDto(Card card){
        return CardDto.builder()
                .type(card.getType())
                .balance(card.getBalance())
                .number(card.getNumber())
                .build();
    }
}
