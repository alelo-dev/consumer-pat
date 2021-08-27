package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Contact;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {
	
    private final ConsumerRepository repository;


    /* Deve listar todos os clientes (cerca de 500) */
    public List<ConsumerDTO> listAllConsumers() {
    	return repository.getAllConsumersList()
    		.stream()
    		.map(this::asConsumerDTO)
    		.collect(Collectors.toList());
    	
    }

    private ConsumerDTO asConsumerDTO(Consumer consumer) {
    	return ConsumerDTO
    				.builder()
    				.id(consumer.getId())
    				.name(consumer.getName())
    				.documentNumber(consumer.getDocumentNumber())
    				.birthDate(consumer.getBirthDate())
    				.address(asAddressDTO(consumer.getAddress()))
    				.contact(asContactDTO(consumer.getContact()))
    				.cards(consumer.getCards()
    						.stream()
    						.map(this::asCardDTO)
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
    
    private CardDTO asCardDTO(Card card) {
    	return new CardDTO(card.getNumber(), card.getType(), card.getBalance());
    }

    /* Cadastrar novos clientes */
    public void createConsumer(Consumer consumer) {
    	consumer.getAddress().setConsumer(consumer);
    	consumer.getContact().setConsumer(consumer);
        repository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    public void updateConsumer(Consumer consumer) {
        repository.save(consumer);
    }


}
