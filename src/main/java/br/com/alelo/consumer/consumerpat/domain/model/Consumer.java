package br.com.alelo.consumer.consumerpat.domain.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;
    @OneToOne
    private Contacts contacts;
    @OneToOne
    private Address address;
    @OneToOne
    private Cards cards;

}
