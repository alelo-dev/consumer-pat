package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer documentNumber;

    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate birthDate;

}
