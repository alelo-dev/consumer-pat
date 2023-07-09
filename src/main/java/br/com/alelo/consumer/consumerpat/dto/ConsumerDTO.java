package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
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

    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    private Integer foodCardNumber;
    private Double foodCardBalance;
    private Integer fuelCardNumber;
    private Double fuelCardBalance;
    private Integer drugstoreCardNumber;
    private Double drugstoreCardBalance;

}
