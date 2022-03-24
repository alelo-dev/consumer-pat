package br.com.alelo.consumer.consumerpat.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    //contacts
    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int postalCode;

    //cards
    int foodCardNumber;
    
    
    /*
     * Excluindo os setters dos saldos, já que não devem ser acessíveis
     * Adicionando métodos para esse fim, pelo mesmo motivo
     */
    @Setter(AccessLevel.NONE)
    double foodCardBalance;

    int fuelCardNumber;
    @Setter(AccessLevel.NONE)
    double fuelCardBalance;

    int drugstoreNumber;
    @Setter(AccessLevel.NONE)
    double drugstoreCardBalance;
    
    
    public void addFoodCardBalance(double value) {
    	this.foodCardBalance += value;
    }
    public void addFuelCardBalance(double value) {
    	this.fuelCardBalance += value;
    }
    public void addDrugStoreCardBalance(double value) {
    	this.drugstoreCardBalance += value;
    }

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
