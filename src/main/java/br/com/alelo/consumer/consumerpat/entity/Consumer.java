package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import jdk.jfr.MemoryAddress;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    //@OneToMany
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "address_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Address address;

    @OneToMany
    @JoinColumn(name = "benefit_id")
    List<BenefitCard> benefits;

    //cards - Entidade Benefícios

   // @JoinColumn(name = "cardNumber")
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    @ManyToOne
//    @JoinColumn(name = "benefit_id")

//    int foodCardNumber;
//    double foodCardBalance;
//
//    int fuelCardNumber;
//    double fuelCardBalance;
//
//    int drugstoreNumber;
//    double drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;

        Contacts contacts = new Contacts();


        String mobilePhoneNumber = ((Consumer) o).contacts.getMobilePhoneNumber();
        String residencePhoneNumber = ((Consumer) o).contacts.getResidencePhoneNumber();
        String phoneNumber = ((Consumer) o).contacts.getResidencePhoneNumber();
        String email    = ((Consumer) o).contacts.getEmail();
        Integer number  = ((Consumer) o).address.getNumber();
        String country  = ((Consumer) o).address.getCountry();
        String street   = ((Consumer) o).address.getStreet();
        int portalCode  = ((Consumer) o).address.getPortalCode();
        String city   = ((Consumer) o).address.getCity();
//        ArrayList<Benefit> benefits = (ArrayList<Benefit>) ((Consumer) o).getBenefits();

        return documentNumber == consumer.documentNumber
                && mobilePhoneNumber == consumer.contacts.getMobilePhoneNumber()
                && residencePhoneNumber == consumer.contacts.getResidencePhoneNumber()
                && phoneNumber == consumer.contacts.getPhoneNumber()
                && number == consumer.address.number
                && portalCode == consumer.address.portalCode
//                && benefits.equals(consumer.benefits)
//                && benefits.equals(consumer.benefits)
                && Objects.equals(id, consumer.id)
                && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.contacts.getEmail()) && Objects.equals(street, consumer.address.street) && Objects.equals(city, consumer.address.city)
                && Objects.equals(country, consumer.address.country);
    }


}
