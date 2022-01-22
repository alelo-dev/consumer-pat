package br.com.alelo.consumer.consumerpat.controller.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConsumerDTO {

    private String name;
    private String documentNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @JsonProperty("phones")
    private List<CreatePhoneDTO> createPhoneDTOS;
    private String email;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;
    @JsonProperty("cards")
    private List<CreateCardDTO> createCardDTOS;
}
