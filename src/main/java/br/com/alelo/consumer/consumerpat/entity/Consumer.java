package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = LengthFieldsBD.LENGTH_200)
    private String name;

    @Column(name = "DOCUMENT_NUMBER", nullable = false)
    private Integer documentNumber;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @OneToOne(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contact contact;

    @OneToOne(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    //cards
//    int foodCardNumber;
//    double foodCardBalance;
//
//    int fuelCardNumber;
//    double fuelCardBalance;
//
//    int drugstoreNumber;
//    double drugstoreCardBalance;

    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
                && mobilePhoneNumber == consumer.mobilePhoneNumber
                && residencePhoneNumber == consumer.residencePhoneNumber
                && phoneNumber == consumer.phoneNumber
                && number == consumer.number
                && portalCode == consumer.portalCode
                && foodCardNumber == consumer.foodCardNumber
                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
                && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
                && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }*/
}
