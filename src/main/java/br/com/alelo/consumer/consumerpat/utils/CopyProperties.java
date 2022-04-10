package br.com.alelo.consumer.consumerpat.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import br.com.alelo.consumer.consumerpat.dto.AddressDto;
import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.ContactDto;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.TypeCard;
import lombok.Data;

@Data
public class CopyProperties {
	
	public static Consumer consumerDtoToConsumer(ConsumerDto consumerDto, boolean update) {
		
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(consumerDto, consumer);
		
		consumer.setCards(new ArrayList<>());
		for (CardDto cardsDto : consumerDto.getCards()) {
		    Card card = new Card();	
		    card.setTypeCard(new TypeCard());
		    BeanUtils.copyProperties(cardsDto.getTypeCard(), card.getTypeCard());
		    BeanUtils.copyProperties(cardsDto , card);	 
		    
		    if(!update) {
		    	card.addBalance(BigDecimal.ZERO);	
		    }
			consumer.getCards().add(card);
		}
		
		
		consumer.setContacts(new ArrayList<>());
		for (ContactDto contactDto : consumerDto.getContacts()) {
		    Contact contact = new Contact();	
		    BeanUtils.copyProperties(contactDto , contact);	    
			consumer.getContacts().add(contact);
		}
		
		consumer.setAddresses(new ArrayList<>());
		for (AddressDto addressDtos : consumerDto.getAddresses()) {
			Address address = new Address();	
		    BeanUtils.copyProperties(addressDtos , address);	    
			consumer.getAddresses().add(address);
		}

		return consumer;
	}
	
}
