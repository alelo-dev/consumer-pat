package br.com.alelo.consumer.consumerpat.entity.consumer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	private Integer mobilePhoneNumber;

	private Integer residencePhoneNumber;
	
	private Integer phoneNumber;

	private String email;
}
