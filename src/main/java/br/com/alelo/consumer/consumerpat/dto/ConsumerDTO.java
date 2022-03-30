package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ConsumerDTO {
    private Integer id;

    private String name;

    private Integer documentNumber;

    private LocalDate birthDate;

    private ContactDTO contact;

    private AddressDTO address;

    private List<CardDTO> cards;
}
