package br.com.alelo.consumer.consumerpat.entity;


import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Entity(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "documentNumber")
    private int documentNumber;
    
    @Column(name = "birthDate")
    private LocalDate birthDate;
    
    @OneToOne(cascade =  CascadeType.ALL)
    private Contact contact;
    
    @OneToOne(cascade =  CascadeType.ALL)
    private Address address;
    
    @OneToOne(cascade =  CascadeType.ALL)
    private Cards cards;

}
