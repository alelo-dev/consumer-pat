package br.com.alelo.consumer.consumerpat.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerResponse {

    private Long id;

    private String name;

    private String documentNumber;

    private LocalDate birthDate;

    private String mobilePhoneNumber;

    private String residencePhoneNumber;

    private String email;

    private String street;

    private Integer numberResidency;

    private String city;

    private String country;

    private String postalCode;

    private List<CardResponse> cards;
}
