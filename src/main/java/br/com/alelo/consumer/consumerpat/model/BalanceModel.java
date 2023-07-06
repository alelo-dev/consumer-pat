package br.com.alelo.consumer.consumerpat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceModel {

    @Positive(message = "Value must be positive")
    BigDecimal value;

    Integer cardNumber;

}
