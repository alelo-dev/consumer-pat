package br.com.alelo.consumer.consumerpat.model;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private Long documentNumber;
    
    private Date birthDate;

    @Embedded
    private Contact contact; 

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;
    
    
    public void credit(Long cardNumber, double value){
    	Card card = findCard(cardNumber);
    	card.setBalance(value);
    }


	private Card findCard(Long cardNumber) {
		return cards.stream()//
    			.filter(c -> Objects.equals(c.getNumber(), cardNumber))//
    			.findFirst()//
    			.orElseThrow( () -> new IllegalArgumentException("Cartão não encontrado: " + cardNumber));
	}

	public void buy(Long cardNumber, double value) {		
		Card card = findCard(cardNumber);
		card.debit(value);
	}
    
}
