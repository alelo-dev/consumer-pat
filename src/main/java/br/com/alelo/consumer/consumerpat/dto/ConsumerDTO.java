package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * OBSERVAÇÃO: Nos casos de validar os dados das DTOs de entrada,
 * utilizaria annotations da lib javax.validation.constraints, porém,
 * requisito do projeto de não adicionar libs impediu tal coisa.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
