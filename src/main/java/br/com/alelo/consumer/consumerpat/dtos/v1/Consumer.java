package br.com.alelo.consumer.consumerpat.dtos.v1;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    private Integer id;
    private String name;
    private int documentNumber;
    private LocalDate birthDate;

    // contacts
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    // Address
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    // cards
    private int foodCardNumber;
    private BigDecimal foodCardBalance;

    private Integer fuelCardNumber;
    private BigDecimal fuelCardBalance;

    private Integer drugstoreNumber;
    private BigDecimal drugstoreCardBalance;

}
