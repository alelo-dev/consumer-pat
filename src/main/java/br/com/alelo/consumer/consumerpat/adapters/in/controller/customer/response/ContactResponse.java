package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String email;
}
