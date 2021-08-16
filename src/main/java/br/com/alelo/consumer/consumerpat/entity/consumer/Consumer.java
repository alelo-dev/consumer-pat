package br.com.alelo.consumer.consumerpat.entity.consumer;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.alelo.consumer.consumerpat.entity.card.Card;
import lombok.Data;


@Data
@Entity(name = "CONSUMER")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    private String name;
        
    private Integer documentNumber;
        
    private LocalDate birthDate;
        
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
       
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
     
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;  
}
