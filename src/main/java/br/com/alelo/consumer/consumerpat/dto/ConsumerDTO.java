package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @NotNull
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer documentNumber;

    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate birthDate;

    @NotNull
    private Integer phoneNumber;
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private String email;

    @NotNull
    private Integer portalCode;
    private String street;
    private Integer number;
    private String city;
    private String country;

    @NotNull
    @Size(min = 16, max = 16, message = "O número deve ter 16 dígitos")
    private Long foodCardNumber;
    private Double foodCardBalance;

    @NotNull
    @Size(min = 16, max = 16, message = "O número deve ter 16 dígitos")
    private Long fuelCardNumber;
    private Double fuelCardBalance;

    @NotNull
    @Size(min = 16, max = 16, message = "O número deve ter 16 dígitos")
    private Long drugstoreCardNumber;
    private Double drugstoreCardBalance;

}
