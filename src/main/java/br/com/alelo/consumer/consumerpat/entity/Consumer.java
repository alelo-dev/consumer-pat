package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "birthDate")
    private Date birthDate;

// Contacts

    @Column(name = "mobilePhoneNumber")
    private String mobilePhoneNumber;

    @Column(name = "residencePhoneNumber")
    private String residencePhoneNumber;

    @Column(name = "phoneNumber")
    private String phoneNumber;

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

    @Column(name = "postalCode")
    private int postalCode;

// Cards

    @Column(name = "foodCardNumber", unique = true)
    private int foodCardNumber;

    @Column(name = "foodCardBalance")
    private BigDecimal foodCardBalance;

    @Column(name = "fuelCardNumber", unique = true)
    private int fuelCardNumber;

    @Column(name = "fuelCardBalance")
    private BigDecimal fuelCardBalance;

    @Column(name = "drugstoreNumber", unique = true)
    private int drugstoreNumber;

    @Column(name = "drugstoreCardBalance")
    private BigDecimal drugstoreCardBalance;

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
            && foodCardNumber == consumer.foodCardNumber && consumer.foodCardBalance.compareTo(foodCardBalance) == 0
            && fuelCardNumber == consumer.fuelCardNumber && consumer.fuelCardBalance.compareTo(fuelCardBalance) == 0
            && drugstoreNumber == consumer.drugstoreNumber && consumer.drugstoreCardBalance.compareTo(drugstoreCardBalance) == 0
            && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
            && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
            && Objects.equals(country, consumer.country);
    }

}
