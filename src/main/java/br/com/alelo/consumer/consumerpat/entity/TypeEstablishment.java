package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeEstablishment implements Serializable {
		
    private static final long serialVersionUID = 7264482008103005850L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTypeEstablishment;
	
    private String typeEstablishment;
    
}
