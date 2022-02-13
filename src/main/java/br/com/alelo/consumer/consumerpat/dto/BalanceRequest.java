package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalanceRequest {

    @JsonProperty
    private String cardNumber;
    @JsonProperty
    private Double value;
    @JsonProperty
    private CardType cardType;
}
