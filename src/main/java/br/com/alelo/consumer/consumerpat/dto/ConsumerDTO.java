package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ConsumerDTO {
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Integer documentNumber;

    private LocalDate birthDate;

    @NonNull
    private ContactDTO contact;

    @NonNull
    private AddressDTO address;

    @NonNull
    private List<CardDTO> cards;
}
