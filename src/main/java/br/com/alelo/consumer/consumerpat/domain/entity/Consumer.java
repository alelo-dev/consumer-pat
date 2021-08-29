package br.com.alelo.consumer.consumerpat.domain.entity;


import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

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
    
    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Contact contact;
    
    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Card> cards = new LinkedHashSet<Card>();



}
