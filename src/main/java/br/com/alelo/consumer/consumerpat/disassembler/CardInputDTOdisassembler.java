package br.com.alelo.consumer.consumerpat.disassembler;

import br.com.alelo.consumer.consumerpat.dto.input.CardInputDTO;
import br.com.alelo.consumer.consumerpat.dto.input.ConsumerInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CardInputDTOdisassembler {

    public Card toDomainObject (CardInputDTO cardInputDTO){
            Card card = new Card();
            card.setCardType(cardInputDTO.getCardType());
            card.setNumber(cardInputDTO.getCardNumber());
            card.setBalance(cardInputDTO.getBalance());

            Consumer consumer = new Consumer();
            consumer.setId(cardInputDTO.getConsumerId());
            card.setConsumer(consumer);

            return card;
    }
}
