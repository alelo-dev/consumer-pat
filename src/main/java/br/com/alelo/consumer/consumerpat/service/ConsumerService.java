package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ContactDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {
	
    private final ConsumerRepository repository;

    private final ExtractRepository extractRepository;


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

    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

//        if(consumer != null) {
//            // é cartão de farmácia
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
//            repository.save(consumer);
//        } else {
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            if(consumer != null) {
//                // é cartão de refeição
//                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
//                repository.save(consumer);
//            } else {
//                // É cartão de combustivel
//                consumer = repository.findByFuelCardNumber(cardNumber);
//                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
//                repository.save(consumer);
//            }
//        }
    }

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (value / 100) * 10;
//            value = value - cashback;
//
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
//            repository.save(consumer);
//
//        }else if(establishmentType == 2) {
//            consumer = repository.findByDrugstoreNumber(cardNumber);
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
//            repository.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (value / 100) * 35;
//            value = value + tax;
//
//            consumer = repository.findByFuelCardNumber(cardNumber);
//            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
//            repository.save(consumer);
//        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}
