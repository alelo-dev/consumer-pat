package br.com.alelo.consumer.consumerpat.converter;

import java.time.LocalDateTime;

import br.com.alelo.consumer.consumerpat.dto.ConsumerContactRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerContactResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Contact;

public class ContactConverter {
	
	public static Contact toEntity(ConsumerContactRequestDTO consumerContactRequestDTO) {
		
		Contact contact = new Contact();
		
		if(consumerContactRequestDTO != null) {
			contact.setId(consumerContactRequestDTO.getId());
			contact.setPhoneType(consumerContactRequestDTO.getPhoneType());
			contact.setPhoneNumber(consumerContactRequestDTO.getPhoneNumber());
			contact.setDddNumber(consumerContactRequestDTO.getDddNumber());
			contact.setCreatedAt(LocalDateTime.now());
		}
		
		return contact;
	}
	
	public static ConsumerContactResponseDTO toDTO(Contact contact) {
		
		ConsumerContactResponseDTO consumerContactResponseDTO = new ConsumerContactResponseDTO();
		
		if(consumerContactResponseDTO != null) {
			consumerContactResponseDTO.setId(contact.getId());
			consumerContactResponseDTO.setPhoneType(contact.getPhoneType());
			consumerContactResponseDTO.setPhoneNumber(contact.getPhoneNumber());
			consumerContactResponseDTO.setDddNumber(contact.getDddNumber());
		}
		
		return consumerContactResponseDTO;
	}
	
	public static Contact updateTarget(final Contact target, final ConsumerContactRequestDTO origin) {
		
		
		if(origin != null) {
			target.setId(origin.getId());
			target.setPhoneType(origin.getPhoneType());
			target.setPhoneNumber(origin.getPhoneNumber());
			target.setDddNumber(origin.getDddNumber());
			target.setCreatedAt(LocalDateTime.now());
		}
		
		return target;
		
	}

}
