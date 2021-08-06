package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.enuns.ContactTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

	private Long id;
	private ContactTypeEnum type; 
	private String value;
	
	public static ContactDTO to(Contact contact) {
		return new ContactDTO(contact.getId(), contact.getType(), contact.getValue());
	}
	
	public Contact toEntity() {
		return new Contact(id, type, value, null);
	}
}
