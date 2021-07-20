package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String documentNumber;
    private Date birthDate;

    public Consumer(Date birthDate, String documentNumber, String name) {
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentNumber=" + documentNumber +
                ", birthDate=" + birthDate +
                '}';
    }

    //    int mobilePhoneNumber;
//    int residencePhoneNumber;
//    int phoneNumber;
//    String email;
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
//
//
//
//    public Consumer(Date birthDate, String city, String country, int documentNumber, double drugstoreCardBalance, int drugstoreNumber, String email, double foodCardBalance, int foodCardNumber, double fuelCardBalance, int fuelCardNumber, int mobilePhoneNumber, String name, int number, int phoneNumber, int portalCode, int residencePhoneNumber, String street) {
//    }
//
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
