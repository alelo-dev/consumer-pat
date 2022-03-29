package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ConsumerDTO {
    Integer id;

    String name;

    Integer documentNumber;

    LocalDate birthDate;

    ContactDTO contact;

    AddressDTO address;

    List<CardDTO> cards;
}
