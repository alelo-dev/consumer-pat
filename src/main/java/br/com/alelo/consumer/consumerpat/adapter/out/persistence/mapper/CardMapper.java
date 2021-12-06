package br.com.alelo.consumer.consumerpat.adapter.out.persistence.mapper;

import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;

public class CardMapper {

  public static Card mapToDomain(final CardEntity cardEntity) {

    return Card.builder().number(cardEntity.getNumber()).balance(cardEntity.getBalance())
        .type(CardType.valueOf(cardEntity.getType().toString())).build();

  }
 
}
