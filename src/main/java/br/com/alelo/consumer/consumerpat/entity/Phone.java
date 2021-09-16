package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Phone {
    
    public enum TYPE_FHONE { MOBILE , RESIDENCE , UNKNOWN }
      
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private TYPE_FHONE typeFhone;


}