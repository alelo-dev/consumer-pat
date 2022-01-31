package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.domain.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;



@Service
public class CardMapper {

    public Card dtoToEntity(CardDTO dto){
        var card = new Card();
        BeanUtils.copyProperties(dto, card);
        return card;
    }


    public CardDTO entityToDTO(Card entity){
        var dto = new CardDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public CardCreateResponseDTO newEntityToDTO(Card entity){
        var dto =  CardCreateResponseDTO.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }


}
