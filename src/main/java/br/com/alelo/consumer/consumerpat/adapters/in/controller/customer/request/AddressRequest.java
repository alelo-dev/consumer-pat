package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "streetName is required")
    private String street;
    @NotBlank(message = "number is required")
    private String number;
    @NotBlank(message = "city is required")
    private String city;
    @NotBlank(message = "country is required")
    private String country;
    @NotBlank(message = "postal is required")
    private String postalCode;
}
