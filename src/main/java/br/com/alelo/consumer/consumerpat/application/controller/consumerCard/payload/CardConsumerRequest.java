package br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardConsumerRequest {

    @Valid
    @NotNull(message = "Card is required.")
    private Card card;
}
