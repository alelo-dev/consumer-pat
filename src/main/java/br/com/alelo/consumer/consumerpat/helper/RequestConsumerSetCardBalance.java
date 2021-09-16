package br.com.alelo.consumer.consumerpat.helper;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestConsumerSetCardBalance {

    @NotNull
    private BigDecimal value;
    @NotEmpty
    private String cardNumber;
}