package br.com.alelo.consumer.consumerpat.domain.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int documentNumber;
    private LocalDate birthDate;
    @OneToOne(cascade=CascadeType.ALL)
    private Contacts contacts;
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Cards> cards;
}
