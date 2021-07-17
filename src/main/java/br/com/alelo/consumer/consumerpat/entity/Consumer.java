package br.com.alelo.consumer.consumerpat.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "id")
@Builder
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;
    private String name;
    private String documentNumber;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private List<Contacts> contactsList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private List<Card> cardsList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private List<Address> addressList;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Consumer consumer = (Consumer) o;
//        return documentNumber == consumer.documentNumber
//                && mobilePhoneNumber == consumer.mobilePhoneNumber
//                && residencePhoneNumber == consumer.residencePhoneNumber
//                && phoneNumber == consumer.phoneNumber
//                && number == consumer.number
//                && portalCode == consumer.portalCode
//                && foodCardNumber == consumer.foodCardNumber
//                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
//                && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
//                && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
//                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
//                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
//                && Objects.equals(country, consumer.country);
//    }
}
