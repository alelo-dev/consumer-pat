package br.com.alelo.consumer.consumerpat.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EstablishmentRequest {

    @JsonProperty("establishment_type")
    private int establishmentType;

    @JsonProperty("establishment_name")
    private String establishmentName;

    @JsonProperty("product_description")
    private String productDescription;
    @JsonProperty("purchase_value")
    private double value;

    @JsonProperty("card")
    private CardRequest card;
}
