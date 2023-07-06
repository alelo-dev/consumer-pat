package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "birth_date")
    private Date birthDate;

// Contacts

    @Column(name = "mobile_phone_number")
    private int mobilePhoneNumber;

    @Column(name = "residence_phone_number")
    private int residencePhoneNumber;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "email")
    private String email;

// Address

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private int number;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private int postalCode;

// Cards

    @Column(name = "food_card_number", unique = true)
    private int foodCardNumber;

    @Column(name = "food_card_balance")
    private double foodCardBalance;

    @Column(name = "fuel_card_number", unique = true)
    private int fuelCardNumber;

    @Column(name = "fuel_card_balance")
    private double fuelCardBalance;

    @Column(name = "drugstore_number", unique = true)
    private int drugstoreNumber;

    @Column(name = "drugstore_card_balance")
    private double drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
            && mobilePhoneNumber == consumer.mobilePhoneNumber
            && residencePhoneNumber == consumer.residencePhoneNumber
            && phoneNumber == consumer.phoneNumber
            && number == consumer.number
            && postalCode == consumer.postalCode
            && foodCardNumber == consumer.foodCardNumber
            && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
            && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
            && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
            && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
            && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
            && Objects.equals(country, consumer.country);
    }

}
