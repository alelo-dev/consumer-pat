package br.com.alelo.consumer.consumerpat.entity.mapper;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.dto.CardDTO;
import org.springframework.beans.BeanUtils;

public class CardMapper {

    public static Card dtoToEntity(CardDTO dto){
        Card card = new Card();
        BeanUtils.copyProperties(dto, card);
        return card;
    }


    public static CardDTO entityToDTO(Card entity){
        CardDTO dto = new CardDTO();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

}
