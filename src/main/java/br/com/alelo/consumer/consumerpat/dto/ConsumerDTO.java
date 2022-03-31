package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConsumerDTO {
    private Long consumerId;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    private String street;
    private Integer addressNumber;
    private String city;
    private String country;
    private int portalCode;

    private List<CardDTO> cards;


}
