package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
    private Integer foodCardNumber;
    private Double foodCardBalance;

    @NotNull
    private Integer fuelCardNumber;
    private Double fuelCardBalance;

    @NotNull
    private Integer drugstoreCardNumber;
    private Double drugstoreCardBalance;

}
