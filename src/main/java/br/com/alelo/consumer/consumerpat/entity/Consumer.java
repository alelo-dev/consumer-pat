package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import jdk.jfr.MemoryAddress;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    //contacts - Entidade de telefone
    @ManyToOne
    @JoinColumn(name = "contacts_id")
    Contacts contacts;
    //Address - Entidade de Endereços
//    @ManyToMany
//    Address address;

    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards - Entidade Benefícios
    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreNumber;
    double drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;

        Contacts contacts = new Contacts();


        String mobilePhoneNumber = ((Consumer) o).contacts.getMobilePhoneNumber();
        String residencePhoneNumber = ((Consumer) o).contacts.getResidencePhoneNumber();
        String phoneNumber = ((Consumer) o).contacts.getResidencePhoneNumber();
        String email = ((Consumer) o).contacts.getEmail();

        return documentNumber == consumer.documentNumber
                && mobilePhoneNumber == consumer.contacts.getMobilePhoneNumber()
                && residencePhoneNumber == consumer.contacts.getResidencePhoneNumber()
                && phoneNumber == consumer.contacts.getPhoneNumber()
                && number == consumer.number
                && portalCode == consumer.portalCode
                && foodCardNumber == consumer.foodCardNumber
                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
                && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
                && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.id)
                && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.contacts.getEmail()) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }


}
