package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Contact {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
	
	int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

}
