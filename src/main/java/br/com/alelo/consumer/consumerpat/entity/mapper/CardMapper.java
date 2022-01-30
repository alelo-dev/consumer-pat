package br.com.alelo.consumer.consumerpat.entity.mapper;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardDTO;
import org.springframework.beans.BeanUtils;

public class CardMapper {

    private CardMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Card dtoToEntity(CardDTO dto){
        var card = new Card();
        BeanUtils.copyProperties(dto, card);
        return card;
    }


    public static CardDTO entityToDTO(Card entity){
        var dto = new CardDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static CardCreateResponseDTO newEntityToDTO(Card entity){
        var dto =  CardCreateResponseDTO.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
