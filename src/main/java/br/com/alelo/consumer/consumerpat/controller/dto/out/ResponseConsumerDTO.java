package br.com.alelo.consumer.consumerpat.controller.dto.out;

import br.com.alelo.consumer.consumerpat.controller.dto.in.CreateCardDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.in.CreatePhoneDTO;
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
    private Integer documentNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    //contacts
    private List<CreatePhoneDTO> createPhoneDTOS;
    private String email;
    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;
    private List<CreateCardDTO> createCardDTOS; //cards
}
