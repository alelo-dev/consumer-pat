package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardBalanceResponseV1 {

    private Long number;
    private Double balance;

    public static CardBalanceResponseV1 transformToResponse(Card card) {
        return CardBalanceResponseV1.builder().number(card.getNumber()).balance(card.getBalance()).build();
    }

}
