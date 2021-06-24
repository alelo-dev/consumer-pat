package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
class ConsumerBaseDto {
    private String name;
    private int documentNumber;
    private LocalDate birthDate;
}
