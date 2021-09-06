package br.com.alelo.consumer.consumerpat.entity;

//TODO Retirado os imports desnecessarios.

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

//TODO NAO FOI DEFINIDO OS CAMPOS OBRIGATORIOS.
@Data
@Entity
public class Consumer {
	

//TODO definir modificador de acesso aos paramentros.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String documentNumber; //TODO para numero de documento melhor colocar como String
    
//TODO como é uma data, o melhor é definir o TemporalType.DATE.
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    

//TODO Responsabilidade unica, esses parametros nao sao do Consumer e sim do Contracts
    //contacts
//    int mobilePhoneNumber;
//    int residencePhoneNumber;
//    int phoneNumber;
//    String email;
    @OneToOne(mappedBy = "consumer")
    private Contracts contracts;

//TODO Responsabilidade unica, esses parametros nao sao do Consumer e sim do Address
    //Address
//    String street;
//    int number;
//    String city;
//    String country;
//    int portalCode;
    @OneToOne(mappedBy = "consumer")
    private Address address;

//TODO Responsabilidade unica, esses parametros nao sao do Consumer e sim do Cards
    //cards
//    int foodCardNumber;
//    double foodCardBalance;
//
//    int fuelCardNumber;
//    double fuelCardBalance;
//
//    int drugstoreNumber;
//    double drugstoreCardBalance;
    @OneToMany
    @JoinColumn(name = "card_id")
    private List<Cards> cards;

    
//    TODO Como estamos utilizando Lombok, especificamente o @Data nao se faz necessario fazer o override desse metodo.
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
