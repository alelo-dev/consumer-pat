package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

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

    /* Review: Por questões de arredondamento é aconselhavel utilizar BigDecimal quando se trata de valore monetarios */

    @Positive(message = "Value must be positive")
    @NotNull(message = "Value cannot be null")
    BigDecimal value;

}
