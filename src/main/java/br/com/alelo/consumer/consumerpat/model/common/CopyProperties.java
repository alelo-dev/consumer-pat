package br.com.alelo.consumer.consumerpat.model.common;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.entity.card.Category;
import br.com.alelo.consumer.consumerpat.entity.consumer.Address;
import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.entity.consumer.Contact;
import br.com.alelo.consumer.consumerpat.model.dto.card.CardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.card.CategoryDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.AddressDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ContactDTO;

public class CopyProperties {
	
	private CopyProperties() {}

	public static ConsumerDTO consumerToDto(Consumer consumer) {
		ConsumerDTO consumerDTO = createObjConsumerDTO();
		BeanUtils.copyProperties(consumer, consumerDTO);		
		BeanUtils.copyProperties(consumer.getAddress(), consumerDTO.getAddress());
		BeanUtils.copyProperties(consumer.getContact(), consumerDTO.getContact());		
		for (Card card : consumer.getCards()) {
		    CardDTO cardDTO = new CardDTO(); 
		    cardDTO.setCategory(new CategoryDTO());
		    BeanUtils.copyProperties(card , cardDTO);
		    BeanUtils.copyProperties(card.getCategory() , cardDTO.getCategory());
			consumerDTO.getCards().add(cardDTO);
		}		
		
		return consumerDTO;
	}
	
	public static Consumer dtoToConsumer(ConsumerDTO consumerDTO) {
		Consumer consumer = createObjConsumer();		
		BeanUtils.copyProperties(consumerDTO, consumer);
		BeanUtils.copyProperties(consumerDTO.getAddress(), consumer.getAddress());
		BeanUtils.copyProperties(consumerDTO.getContact(), consumer.getContact());		
		for (CardDTO cardDTO : consumerDTO.getCards()) {
		    Card card = new Card();	
		    card.setCategory(new Category());
		    BeanUtils.copyProperties(cardDTO , card);
		    BeanUtils.copyProperties(cardDTO.getCategory() , card.getCategory());		    
			consumer.getCards().add(card);
		}		
				
		return consumer;
	}	
	
	private static Consumer createObjConsumer() {
		Consumer consumer = new Consumer();		
		consumer.setAddress(new Address());
		consumer.setContact(new Contact());		
		consumer.setCards(new ArrayList<>());
		return consumer;
	}
	
	private static ConsumerDTO createObjConsumerDTO() {
		ConsumerDTO consumerDTO = new ConsumerDTO();		
		consumerDTO.setAddress(new AddressDTO());
		consumerDTO.setContact(new ContactDTO());
		consumerDTO.setCards(new ArrayList<>());
		return consumerDTO;
	}
}
