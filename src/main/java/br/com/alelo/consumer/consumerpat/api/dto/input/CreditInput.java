package br.com.alelo.consumer.consumerpat.api.dto.input;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditInput {

    private int cardNumber;
    private BigDecimal value;
}
