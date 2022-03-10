package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private int portalCode;

    @OneToOne
    private Consumer consumer;


}
