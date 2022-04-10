package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable {
	
	private static final long serialVersionUID = -8853261142760251541L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
	private UUID idAdress;
 	
    private String street;
    
    private Integer number;
    
    private String city;
    
    private String country;
    
    private String portalCode;

}
