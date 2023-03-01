package br.com.alelo.consumer.consumerpat.common.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.CardNumber;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class NewCard {

    @CardNumber
    @NotEmpty(message = "Card number is required.")
    String number;

    @NotNull(message = "Card type is required.")
    @Positive(message = "Card type must be valid.")
    Integer type;
}
