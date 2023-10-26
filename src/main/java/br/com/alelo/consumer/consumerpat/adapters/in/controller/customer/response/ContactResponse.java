package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response;

import lombok.Data;

@Data
public class ContactResponse {
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String email;
}
