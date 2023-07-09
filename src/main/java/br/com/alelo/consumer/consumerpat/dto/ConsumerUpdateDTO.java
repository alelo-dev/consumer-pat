package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerUpdateDTO {

    private String name;
    private Integer documentNumber;
    private LocalDate birthDate;

    // conforme regras negociais, complementar campos que podem ser atualizados

}
