package br.com.alelo.consumer.consumerpat.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Classe de objetos de transporte dos dados do consumidor.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class ConsumerDto extends Dto {

    private BigInteger id;

    // CONSUMER PERSONAL DATA
    private String name;
    private Long documentNumber;
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
    private BigInteger foodCardNumber;
    private BigDecimal foodCardBalance;

    private BigInteger fuelCardNumber;
    private BigDecimal fuelCardBalance;

    private BigInteger drugstoreCardNumber;
    private BigDecimal drugstoreCardBalance;

}
