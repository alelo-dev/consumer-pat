package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyModel {

    Integer type;

    String Name;

    @Pattern(regexp = "/^[0-9]*$/")
    Integer cardNumber;

    String productDescription;

    Double value;

}
