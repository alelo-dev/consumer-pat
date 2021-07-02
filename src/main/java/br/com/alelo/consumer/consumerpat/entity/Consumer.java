package br.com.alelo.consumer.consumerpat.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Column(name = "document_number", unique = true)
    private String documentNumber;
    @Column(name = "birth_date")
    private LocalDate birthDate;

//    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;


    @Builder
    public Consumer(String name, String documentNumber, LocalDate birthDate) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
    }

    //contacts
    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;
//
//    //Address
//    String street;
//    int number;
//    String city;
//    String country;
//    int portalCode;
//
//    //cards
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
        return name.equals(consumer.name) &&
                documentNumber.equals(consumer.documentNumber) &&
                birthDate.equals(consumer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, documentNumber, birthDate);
    }
}
