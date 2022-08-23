package br.com.alelo.consumer.consumerpat.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.model.Address;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Contact;

@Component
public class MapConsumerDto {

	public Consumer create(ConsumerDto consumerDto) {
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(consumerDto, consumer);
		
		Contact contact = new Contact();
		BeanUtils.copyProperties(consumerDto.getContact(), contact);		
		consumer.setContact(contact);
		
		Address address = new Address();
		BeanUtils.copyProperties(consumerDto.getAddress(), address);
		consumer.setAddress(address);
		
		List<CardDto> cardDtos = consumerDto.getCards();
		List<Card> cards = new ArrayList<>();
		cardDtos.forEach(dto -> {
			Card card = new Card();
			BeanUtils.copyProperties(dto, card);	
			cards.add(card);
		});
		consumer.setCards(cards);			
			
		return consumer;
	}
	
	public void update(ConsumerDto consumerDto, Consumer consumer) {		
		BeanUtils.copyProperties(consumerDto, consumer);
		
		Contact contact = consumer.getContact();
		BeanUtils.copyProperties(consumerDto.getContact(), contact);		
		
		Address address = consumer.getAddress();
		BeanUtils.copyProperties(consumerDto.getAddress(), address);
			
	}

}
