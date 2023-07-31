package br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload;

import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload.dto.CardsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardConsumerResponse {
    Set<CardsDto> cards;

    public static CardConsumerResponse of(Set<CardsDto> cardsBalance) {
        return new CardConsumerResponse(cardsBalance);
    }
}
