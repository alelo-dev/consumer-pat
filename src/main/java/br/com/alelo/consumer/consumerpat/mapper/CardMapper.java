package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.entity.Card;
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
    private ModelMapper mapper;

    public Card toRequest(CardRequest consumerRequest) {
        return mapper.map(consumerRequest, Card.class);
    }

    public CardResponse toResponse(Card card) {
        return mapper.map(card, CardResponse.class);
    }

}
