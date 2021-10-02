package br.com.alelo.consumer.consumerpat.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String documentNumber;
    private Date birthDate;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.PERSIST)
    private Contact contact;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.PERSIST)
    private List<Card> cards = new ArrayList<>();
}
