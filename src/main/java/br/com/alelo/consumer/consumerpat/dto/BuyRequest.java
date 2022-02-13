package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.contants.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BuyRequest {

    @JsonProperty
    private EstablishmentType establishmentType;
    @JsonProperty
    private String establishmentName;
    @JsonProperty
    private String cardNumber;
    @JsonProperty
    private String productDescription;
    @JsonProperty
    private Double value;
}
