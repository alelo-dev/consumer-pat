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
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 7680993063414343779L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID idContact;
	
    private String mobilePhoneNumber;
    
    private String residencePhoneNumber;
    
    private String phoneNumber;
    
    private String email;

}
