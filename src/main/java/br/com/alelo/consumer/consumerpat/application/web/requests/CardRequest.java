package br.com.alelo.consumer.consumerpat.application.web.requests;

import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardRequest {
    @JsonProperty("establishment_type")
    private EstablishmentType establishmentType;
    @JsonProperty("card_number")
    private String cardNumber;
    private double value;

}
