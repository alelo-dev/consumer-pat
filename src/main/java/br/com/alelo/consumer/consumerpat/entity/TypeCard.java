package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TypeCard implements Serializable {
		
    private static final long serialVersionUID = 7264482008103005850L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTypeCard;
	
    private String typeCard;
}
