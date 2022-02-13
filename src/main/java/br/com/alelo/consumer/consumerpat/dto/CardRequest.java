package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CardRequest {

    @JsonProperty
    private CardType type;
    @JsonProperty(required = true)
    private String cardNumber;
}
