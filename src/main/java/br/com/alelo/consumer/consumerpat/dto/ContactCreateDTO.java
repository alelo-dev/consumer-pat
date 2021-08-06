package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.enuns.ContactTypeEnum;
import lombok.Data;

@Data
public class ContactCreateDTO {

	private ContactTypeEnum type; 
	private String value;
	
	public Contact toEntity() {
		return new Contact(null, type, value, null);
	}
}
