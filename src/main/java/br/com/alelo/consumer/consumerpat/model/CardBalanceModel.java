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
public class CardBalanceModel {

    BigDecimal value;

    @Pattern(regexp = "/^[0-9]*$/")
    Integer cardNumber;

}
