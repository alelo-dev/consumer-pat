package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;
import br.com.alelo.consumer.consumerpat.model.converter.TypeCardConverter;
import lombok.Data;

@Data
@Entity
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
	@Convert(converter = TypeCardConverter.class)
	private TypeCard type;


}
