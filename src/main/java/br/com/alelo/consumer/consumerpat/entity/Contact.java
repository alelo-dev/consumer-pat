package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //contacts
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    @OneToOne
    private Consumer consumer;

}
