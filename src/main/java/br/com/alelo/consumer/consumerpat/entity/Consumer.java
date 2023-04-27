package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Builder
@Data
@Table(indexes = {
        @Index(name = "idx_foodCardNumber", columnList = "foodCardNumber"),
        @Index(name = "idx_fuelCardNumber", columnList = "fuelCardNumber"),
        @Index(name = "idx_drugstoreNumber", columnList = "drugstoreNumber")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    //TODO: segmentar os dados em mais de uma entidade, tais como dados de contato endereço mas princippalmente o registro dos cartões do cliente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    Integer documentNumber;
    Date birthDate;

    //contacts
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;

    //Address
    String street;
    Integer number;
    String city;
    String country;
    Integer portalCode;

    //cards
    Long foodCardNumber;
    Double foodCardBalance;

    Long fuelCardNumber;
    Double fuelCardBalance;

    Long drugstoreNumber;
    Double drugstoreCardBalance;

}
