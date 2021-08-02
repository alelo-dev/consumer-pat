package br.com.alelo.consumer.consumerpat.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Value;

@Value
public class UpdateConsumerDTO {

    String name;

    String documentNumber;

    LocalDate birthDate;

    String postalCode;

    String streetName;

    String streetNumber;

    String city;

    String country;

    List<UpdateContactDTO> contacts;

    List<UpdateCardDTO> cards;

}
