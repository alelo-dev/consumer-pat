package br.com.alelo.consumer.consumerpat.adapter.in.web.mapper;

import br.com.alelo.consumer.consumerpat.adapter.in.web.model.CardBalanceUpdateDTO;
import br.com.alelo.consumer.consumerpat.domain.Card;

public class CardBalanceUpdateDTOMapper {

  public static CardBalanceUpdateDTO mapToDTO(final Card card) {

    return CardBalanceUpdateDTO.builder().cardNumber(card.getNumber()).cardNumber(card.getNumber())
        .build();
  }
}
