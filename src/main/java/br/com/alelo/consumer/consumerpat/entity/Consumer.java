package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer documentNumber;
    private LocalDateTime birthDate;

    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    //cards
    private Integer foodCardNumber;
    private BigDecimal foodCardBalance;

    private Integer fuelCardNumber;
    private BigDecimal fuelCardBalance;

    private Integer drugstoreNumber;
    private BigDecimal drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.getDocumentNumber()
                && mobilePhoneNumber == consumer.getMobilePhoneNumber()
                && residencePhoneNumber == consumer.getResidencePhoneNumber()
                && phoneNumber == consumer.getPhoneNumber()
                && number == consumer.getNumber()
                && portalCode == consumer.getPortalCode()
                && foodCardNumber == consumer.getFoodCardNumber()
                && consumer.getFoodCardBalance().compareTo(foodCardBalance) == 0
                && fuelCardNumber == consumer.getFuelCardNumber() && consumer.getFuelCardBalance().compareTo(fuelCardBalance) == 0
                && drugstoreNumber == consumer.drugstoreNumber && consumer.getDrugstoreCardBalance().compareTo(drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.getId()) && Objects.equals(name, consumer.getName()) && Objects.equals(birthDate, consumer.getBirthDate())
                && Objects.equals(email, consumer.getEmail()) && Objects.equals(street, consumer.getStreet()) && Objects.equals(city, consumer.getCity())
                && Objects.equals(country, consumer.getCountry());
    }


}
