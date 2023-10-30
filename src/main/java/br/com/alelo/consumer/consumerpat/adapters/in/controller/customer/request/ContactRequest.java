package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ContactRequest {

    @NotBlank(message = "mobile phone number is required")
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    @Email
    @NotBlank(message = "email is required")
    private String email;
}
