package br.com.alelo.consumer.consumerpat.model.mapper;

import org.mapstruct.Mapper;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.dto.CardDTO;

@Mapper(componentModel = "spring")
public interface CardMapper {

	Card toModel(CardDTO vo);

	CardDTO toDTO(Card model);
}
