package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CardResponse {

    @JsonProperty
    private CardType cardType;
    private String consumerDocument;
    private String cardNumber;
    private Double balance;

    private Set<ExtractResponse> extracts;
}
