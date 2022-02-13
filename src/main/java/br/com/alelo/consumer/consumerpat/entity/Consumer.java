package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Builder
@Entity
@EqualsAndHashCode(exclude = "cards")
@ToString(exclude = {"cards"})
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String documentNumber;
    private Date birthDate;

    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;

    //cards
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Card> cards;

    public void addCard(Card card){
        this.cards.add(card);
    }
}
