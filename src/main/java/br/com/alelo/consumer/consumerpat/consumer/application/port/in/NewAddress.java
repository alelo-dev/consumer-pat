package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Value
public class NewAddress {

    @NotEmpty(message = "Street name is required.")
    String streetName;

    @PositiveOrZero(message = "Address number must be valid.")
    int number = 0;

    @NotEmpty(message = "City name is required.")
    String city;

    @NotEmpty(message = "Country is required.")
    String country;

    @NotNull(message = "Postal code is required.")
    @Positive(message = "Postal code must be valid.")
    Integer postalCode;
}
