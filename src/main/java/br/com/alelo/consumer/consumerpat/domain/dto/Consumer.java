package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe DTO que mantem ativo o contrato de request/response legado para consumer e card
 *
 */
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
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
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
