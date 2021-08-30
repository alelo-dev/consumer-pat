package br.com.alelo.consumer.consumerpat.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Contact;
import br.com.alelo.consumer.consumerpat.domain.payload.CardPayload;
import br.com.alelo.consumer.consumerpat.domain.payload.ConsumerPayload;
import br.com.alelo.consumer.consumerpat.domain.response.CardResponse;
import br.com.alelo.consumer.consumerpat.domain.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.exception.CardAlreadyExistsException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {
	
    private final ConsumerRepository repository;
    private final CardRepository cardRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    public Page<ConsumerResponse> listAllConsumers(Pageable page) {
    	return repository.findAll(page)
    		.map(this::asConsumerResponse);
    }

    /* Cadastrar novos clientes */
    public void createConsumer(ConsumerPayload payload) {
        repository.save(asConsumer(payload));
    }

    // Não deve ser possível alterar o saldo do cartão
    public void updateConsumer(Integer id, ConsumerPayload payload) {
        final var consumer = repository.findById(id).orElseThrow(() -> new ConsumerNotFoundException(id));
        
        final var updatedConsumer = asConsumer(payload, consumer);
        updatedConsumer.setId(id);
        
        repository.save(updatedConsumer);
    }
    
    //Utilitários
    private Consumer asConsumer(ConsumerPayload payload, Consumer consumer) {
    	consumer.setName(payload.getName());
    	consumer.setDocumentNumber(payload.getDocumentNumber());
    	consumer.setBirthDate(payload.getBirthDate());
    	consumer.setAddress(asAddress(payload.getAddress(), consumer));
    	consumer.setContact(asContact(payload.getContact(), consumer));
    	
    	final var cards = asCards(payload, consumer);
    	consumer.getCards().clear();
    	consumer.getCards().addAll(cards);
    	return consumer;
    }

    private Consumer asConsumer(ConsumerPayload payload) {
    	return asConsumer(payload, new Consumer());
    }
    
    private Address asAddress(AddressDTO addressDTO, Consumer consumer) {
    	final var address = Optional.ofNullable(consumer.getAddress())
    			.orElseGet(() -> {
    				final var newAddress = new Address();
    				newAddress.setConsumer(consumer);
    				return newAddress;
    				});
    	return asAddress(addressDTO, address);
    }
    
    private Address asAddress(AddressDTO addressDTO, Address address) {
    	address.setStreet(addressDTO.getStreet());
    	address.setNumber(addressDTO.getNumber());
    	address.setCity(addressDTO.getCity());
    	address.setCountry(addressDTO.getCountry());
    	address.setPostalCode(addressDTO.getPostalCode());
    	return address;
    }
    
    private Contact asContact(ContactDTO contactDTO, Consumer consumer) {
    	final var contact = Optional.ofNullable(consumer.getContact())
    			.orElseGet(() -> {
    				final var newContact = new Contact();
    				newContact.setConsumer(consumer);
    				return newContact;
    				});
    	return asContact(contactDTO, contact);
    }
    
    private Contact asContact(ContactDTO contactDTO, Contact contact) {
    	contact.setMobilePhoneNumber(contactDTO.getMobilePhoneNumber());
    	contact.setPhoneNumber(contactDTO.getPhoneNumber());
    	contact.setResidencePhoneNumber(contactDTO.getResidencePhoneNumber());
    	contact.setEmail(contactDTO.getEmail());
    	return contact;
    }

    private Set<Card> asCards(ConsumerPayload payload, Consumer consumer) {
    	final var cardSet = new LinkedHashSet<Card>();
    	final var prevCards = consumer.getCards()
    			.stream()
    			.collect(Collectors.toMap(Card::getNumber, Function.identity()));
    	
    	payload.getCards().forEach(cardPayload -> {
    		var card = prevCards.get(cardPayload.getNumber());
    		
    		if(card != null) {
    			card.setType(cardPayload.getType());
    			card.setConsumer(consumer);
    		} else {
    			if(cardRepository.existsById(cardPayload.getNumber())) {
    				throw new CardAlreadyExistsException(cardPayload.getNumber());
    			}
    			
    			card = asCard(cardPayload, consumer);
    		}
    		cardSet.add(card);
    	});
    	
    	return cardSet;
    }
    
    private Card asCard(CardPayload cardPayload, Consumer consumer) {
    	final var card = new Card();
    	card.setNumber(cardPayload.getNumber());
    	card.setType(cardPayload.getType());
    	card.setConsumer(consumer);
    	return card;
    }
    
    private ConsumerResponse asConsumerResponse(Consumer consumer) {
    	return ConsumerResponse
    				.builder()
    				.id(consumer.getId())
    				.name(consumer.getName())
    				.documentNumber(consumer.getDocumentNumber())
    				.birthDate(consumer.getBirthDate())
    				.address(asAddressDTO(consumer.getAddress()))
    				.contact(asContactDTO(consumer.getContact()))
    				.cards(consumer.getCards()
    						.stream()
    						.map(this::asCardResponse)
    						.collect(Collectors.toSet()))
    				.build();
    }
    
    private AddressDTO asAddressDTO(Address address) {
    	return AddressDTO
				.builder()
				.street(address.getStreet())
				.number(address.getNumber())
				.city(address.getCity())
				.country(address.getCountry())
				.postalCode(address.getPostalCode())
				.build();
    } 
    
    private ContactDTO asContactDTO(Contact contact) {
    	return ContactDTO
    			.builder()
    			.mobilePhoneNumber(contact.getMobilePhoneNumber())
    			.phoneNumber(contact.getPhoneNumber())
    			.residencePhoneNumber(contact.getResidencePhoneNumber())
    			.email(contact.getEmail())
    			.build();
    }
    
    private CardResponse asCardResponse(Card card) {
    	return new CardResponse(card.getNumber(), card.getType(), card.getBalance());
    }

}
