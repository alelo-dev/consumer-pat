package br.com.alelo.consumer.pat.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateConsumerPayload {

    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

}
