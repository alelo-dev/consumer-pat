package br.com.alelo.consumer.consumerpat.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuyRequest {

    private final EstablishmentType establishmentType;
    private final String establishmentName;
    private final int cardNumber;
    private final String productDescription;
    private final double value;
}
