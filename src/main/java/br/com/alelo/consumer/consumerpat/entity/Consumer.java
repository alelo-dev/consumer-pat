package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Consumer extends BaseEntity {

    private String name;

    private int documentNumber;

    private LocalDate birthDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Contact> contactList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Card> cardList;
}
