package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address implements Serializable {
	
	private static final long serialVersionUID = -8853261142760251541L;

	@Id
    private String idAdress;
	
    private String street;
    
    private Integer number;
    
    private String city;
    
    private String country;
    
    private String portalCode;

}
