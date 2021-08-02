package br.com.alelo.consumer.consumerpat.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumerDTO {

    private Integer id;

    private String name;

    private String documentNumber;

    private LocalDate birthDate;

    private String postalCode;

    private String streetName;

    private String streetNumber;

    private String city;

    private String country;

    private List<ContactDTO> contacts;

    private List<CardDTO> cards;

}
