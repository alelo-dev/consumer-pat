package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class ConsumerCreditDto {

    @NotNull(message = "Card number is required")
    @Positive(message = "Card number should be greater than 1")
    private Long cardNumber;

    @NotNull(message = "Value is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=17, fraction=2)
    private BigDecimal value;

}