package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
@Table(name = "CONSUMER")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOCUMENT_NUMBER")
    private Long documentNumber;

    @Column(name = "BIRTH_DATE")
    @CreationTimestamp
    private Date birthDate;

    //contacts
    @Column(name = "MOBILE_PHONE_NUMBER")
    private Integer mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER")
    private Integer residencePhoneNumber;

    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    //Address
    @Column(name = "ADDRESS_STREET")
    private String addressStreet;

    @Column(name = "ADDRESS_NUMBER")
    private Integer addressNumber;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    //cards
    @Column(name = "FOOD_CARD_NUMBER")
    private Long foodCardNumber;

    @Column(name = "FOOD_CARD_BALANCE")
    private BigDecimal foodCardBalance;

    @Column(name = "FUEL_CARD_NUMBER")
    private Long fuelCardNumber;

    @Column(name = "FUEL_CARD_BALANCE")
    private BigDecimal fuelCardBalance;

    @Column(name = "DRUGSTORE_CARD_NUMBER")
    private Long drugstoreCardNumber;

    @Column(name = "DRUGSTORE_CARD_BALANCE")
    private BigDecimal drugstoreCardBalance;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer that = (Consumer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
