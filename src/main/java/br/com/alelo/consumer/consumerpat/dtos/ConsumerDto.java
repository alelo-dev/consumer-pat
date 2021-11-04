package br.com.alelo.consumer.consumerpat.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ConsumerDto implements Serializable {

    private static final long serialVersionUID = -744422068001985404L;
    private Long id;
    private String name;
    @JsonAlias("document-number")
    private Integer documentNumber;
    @JsonAlias("birth-date")
    private LocalDate birthDate;
    @JsonAlias("mobile-phone-number")
    private Integer mobilePhoneNumber;
    @JsonAlias("residence-phone-number")
    private Integer residencePhoneNumber;
    @JsonAlias("phone-number")
    private Integer phoneNumber;
    private String email;
    private String street;
    private Integer number;
    private String city;
    private String country;
    @JsonAlias("portal-code")
    private Integer portalCode;
    @JsonAlias("establishment-type")
    private Integer establishmentType;
    @JsonAlias("food-card-number")
    private Integer foodCardNumber;
    @JsonAlias("food-card-balance")
    private BigDecimal foodCardBalance;
    @JsonAlias("fuel-card-number")
    private Integer fuelCardNumber;
    @JsonAlias("fuel-card-balance")
    private BigDecimal fuelCardBalance;
    @JsonAlias("drugstore-card-number")
    private Integer drugstoreCardNumber;
    @JsonAlias("drugstore-card-balance")
    private BigDecimal drugstoreCardBalance;

}
