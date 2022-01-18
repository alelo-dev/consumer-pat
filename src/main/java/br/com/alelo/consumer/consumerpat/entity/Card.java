package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import lombok.Data;

@Data
public class Card {

    private String number;
    private double balance;
    
	private EstablishmentType tipo;
	
	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="id_consumer", updatable = true, insertable = true)
//	private Consumer consumer;	
}
