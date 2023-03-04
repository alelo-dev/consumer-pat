package br.com.alelo.consumer.consumerpat.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardBalanceRequest {
    @JsonProperty("card_number")
    private String cardNumber;
    private double value;

}
