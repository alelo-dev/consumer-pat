package br.com.alelo.consumer.consumerpat.controller.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConsumerDTO {

    private String name; //NULL
    private Integer documentNumber; //NOT NULL
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate; //NULL

    //contacts
    private Integer mobilePhoneNumber; // not null
    private Integer residencePhoneNumber; // NOT NULL
    private Integer phoneNumber; // NOT NULL
    private String email; // null

    //Address
    private String street; //NULL
    private Integer number; //NOT NULL
    private String city; //NULL
    private String country; //NULL
    private Integer postalCode; //NOT NULL

    //cards
    private Integer foodCardNumber; //NOT NULL
    private Double foodCardBalance; //NOT NULL

    private Integer fuelCardNumber; //NOT NULL
    private Double fuelCardBalance; // NOT NULL

    private Integer drugstoreNumber; //not null
    private Double drugstoreCardBalance; //not null

}
