package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Value
public class NewConsumer {

    @NotEmpty(message = "Name is required.")
    String name;

    @Positive
    @NotNull(message = "Document number is required.")
    int documentNumber;

    @NotNull(message = "Birth date is required.")
    @Past(message = "Birth date cannot must be a past date.")
    LocalDate birthDate;

    @Valid
    Set<NewContact> contacts = new HashSet<>();

    @Valid
    Set<NewAddress> addresses = new HashSet<>();

    @Valid
    Set<NewCard> cards = new HashSet<>();
}
