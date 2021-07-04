package br.com.alelo.consumer.consumerpat.controller.response;

import br.com.alelo.consumer.consumerpat.domain.card.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({ "card_id", "consumer_id", "type", "number_card", "balance"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class CardResponse {

    @JsonProperty("card_id")
    private final long cardId;

    @JsonProperty("consumer_id")
    private final Long consumerId;

    @JsonProperty("type")
    private final CardType type;

    @JsonProperty("card_number")
    private final String numberCard;

    @JsonProperty("balance")
    private final double balance;


}
