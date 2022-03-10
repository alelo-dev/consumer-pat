package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Contact contact;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.PERSIST)
    private List<Card> cards;

    /*
    //cards
    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreNumber;
    double drugstoreCardBalance;
 */

}
