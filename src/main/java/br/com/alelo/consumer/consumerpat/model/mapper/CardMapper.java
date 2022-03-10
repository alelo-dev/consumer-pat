package br.com.alelo.consumer.consumerpat.model.mapper;

import org.mapstruct.Mapper;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.dto.CardVO;

@Mapper(componentModel = "spring")
public interface CardMapper {

	Card voToModel(CardVO vo);
}
