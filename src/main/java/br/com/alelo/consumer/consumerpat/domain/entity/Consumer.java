package br.com.alelo.consumer.consumerpat.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String consumerCode;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    //Address
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    //cards
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "consumer_id")
    private Set<Card> cards;


}
