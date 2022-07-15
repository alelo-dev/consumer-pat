package br.com.alelo.consumer.consumerpat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Objeto de Transporte dos dados do cliente (Consumer).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ConsumerDto {

    private Long id;

    // CONSUMER PERSONAL DATA
    private String name;
    private Integer documentNumber;
    private Date birthDate;

    // CONSUMER CONTACTS
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;

    // CONSUMER ADDRESS
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    // CONSUMER CARDS
    private Integer foodCardNumber;
    private Double foodCardBalance;

    private Integer fuelCardNumber;
    private Double fuelCardBalance;

    private Integer drugstoreCardNumber;
    private Double drugstoreCardBalance;

}
