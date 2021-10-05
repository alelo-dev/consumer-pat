package br.com.alelo.consumer.consumerpat.domain.dto;

import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.domain.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO {

	    private String name;
	    private String documentNumber;
	    private LocalDate birthDate;
	  	private Contact contact;
	  	private Address address;
	
}


