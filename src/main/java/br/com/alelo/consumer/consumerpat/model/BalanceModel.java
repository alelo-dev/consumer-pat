package br.com.alelo.consumer.consumerpat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceModel {

    /* Review: Mantendo double dado a base mas a utilização do BigDecimal seria o ideal para evitar erros de conversões */
    @Positive(message = "Value must be positive")
    Double value;

    Integer cardNumber;

}
