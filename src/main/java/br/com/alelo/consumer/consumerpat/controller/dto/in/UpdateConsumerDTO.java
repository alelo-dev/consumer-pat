package br.com.alelo.consumer.consumerpat.controller.dto.in;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConsumerDTO {

    private String name;
    private Integer documentNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;
    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;

    //cards
    private Integer foodCardNumber; //NOT NULL
    private Double foodCardBalance; //NOT NULL

    private Integer fuelCardNumber; //NOT NULL
    private Double fuelCardBalance; // NOT NULL

    private Integer drugstoreNumber; //not null
    private Double drugstoreCardBalance; //not null

}
