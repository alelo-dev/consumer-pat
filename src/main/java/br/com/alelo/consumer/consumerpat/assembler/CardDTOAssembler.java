package br.com.alelo.consumer.consumerpat.assembler;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResumeDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardDTOAssembler {

    public CardDTO toModel(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setCardNumber(card.getNumber());
        cardDTO.setBalance(card.getBalance());

        ConsumerResumeDTO consumerResumeDTO = new ConsumerResumeDTO();
        consumerResumeDTO.setId(card.getConsumer().getId());
        consumerResumeDTO.setName(card.getConsumer().getName());
        cardDTO.setConsumer(consumerResumeDTO);

        return cardDTO;

    }



    public List<CardDTO> toCollectionModel(List<Card> cards) {
        return cards.stream()
                .map(card -> toModel(card))
                .collect(Collectors.toList());

    }
}
