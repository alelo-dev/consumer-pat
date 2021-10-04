package br.com.alelo.consumer.consumerpat.entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Data
@Entity
@Builder
@Table(name = "CONSUMER")
public class Consumer {

    @Id
    @Column(name = "ID_CONSUMER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    //Contacts
    @Column(name = "MOBILE_PHONE_NUMBER")
    private int mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER")
    private int residencePhoneNumber;

    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    //Address
    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PORTAL_CODE")
    private int portalCode;

    //Cards
    @OneToMany(fetch = FetchType.LAZY,
            cascade ={CascadeType.ALL},
            mappedBy = "consumer")
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return mobilePhoneNumber == consumer.mobilePhoneNumber
                && residencePhoneNumber == consumer.residencePhoneNumber
                && phoneNumber == consumer.phoneNumber
                && number == consumer.number
                && portalCode == consumer.portalCode
                && Objects.equals(id, consumer.id)
                && Objects.equals(name, consumer.name)
                && Objects.equals(documentNumber, consumer.documentNumber)
                && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email)
                && Objects.equals(street, consumer.street)
                && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country)
                && Objects.equals(cards, consumer.cards);
    }

}
