package br.com.alelo.consumer.consumerpat.controller.dto.out;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseConsumerDTO {

    private String name;
    private String documentNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private List<ResponsePhoneDTO> phoneDTOS;
    private String email;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;
    private List<ResponseCardDTO> cardDTOS;

}
