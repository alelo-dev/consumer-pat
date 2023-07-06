package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyModel {


    @NotNull(message = "Establishment type cannot be null")
    Integer establishmentType;

    String establishmentName;

    @NotNull(message = "Card Number cannot be null")
    Integer cardNumber;

    String productDescription;

    @NotNull(message = "Value cannot be null")
    Double value;

}
