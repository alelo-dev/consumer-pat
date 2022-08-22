package br.com.alelo.consumer.consumerpat.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;
    
    private int documentNumber;
    
    private Date birthDate;

    @Embedded
    private Contact contact; 

    @Embedded
    private Address address;
    
    @ElementCollection
    @CollectionTable
    private List<Card> cards;
    
    
}
