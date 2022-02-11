package br.com.alelo.consumer.consumerpat.entity;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "documentNumber", nullable = false)
    private String documentNumber;
    
    @Column(name = "birthDate", nullable = false)
    private Date birthDate;

    //contacts
    @Column(name = "mobilePhoneNumber", nullable = true)
    private Integer mobilePhoneNumber;
    
    @Column(name = "residencePhoneNumber", nullable = true)
    private Integer residencePhoneNumber;
    
    @Column(name = "phoneNumber", nullable = true)
    private Integer phoneNumber;
    
    @Column(name = "email", nullable = true, length = 255)
    private String email;

    //Address
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.REMOVE)
    private List<Address> addresses;

    //cards
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.REMOVE)
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(documentNumber, consumer.documentNumber)
                && mobilePhoneNumber == consumer.mobilePhoneNumber
                && residencePhoneNumber == consumer.residencePhoneNumber
                && phoneNumber == consumer.phoneNumber
                /*&& number == consumer.number
                && portalCode == consumer.portalCode
                && foodCardNumber == consumer.foodCardNumber
                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
                && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
                && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0*/
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email); /*&& Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)*/
                //&& Objects.equals(country, consumer.country);
    }


}
