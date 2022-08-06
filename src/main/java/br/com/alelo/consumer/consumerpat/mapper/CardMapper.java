package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.request.CardRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CardMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Card toEntity(CardRequest cardRequest) {
        return modelMapper.map(cardRequest, Card.class);
    }

    public CardResponse toResponse(Card savedCardForConsumer) {
        return modelMapper.map(savedCardForConsumer, CardResponse.class);
    }
}
