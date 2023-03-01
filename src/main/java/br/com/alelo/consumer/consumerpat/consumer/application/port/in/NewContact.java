package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class NewContact {

    @NotNull(message = "Contact info is required.")
    String info;

    @NotNull(message = "Contact type is required.")
    @Positive(message = "Contact type must be valid.")
    Integer type;
}
