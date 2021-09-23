package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.controller.error.NotFoundException;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
@Transactional
public class ConsumerServiceImp implements ConsumerService {
	
	@Autowired
	public ConsumerRepository repository;
	
	@Autowired
	public ExtractRepository extractRepository;
	
	@Autowired
	public CardRepository cardRepository;

	public ConsumerServiceImp() {

	}

	@Override
	public void setBalance(int cardNumber, double value) {
		Card card = findCard(cardNumber);
		card.setBalance(card.getBalance() + value);
		cardRepository.save(card);
	}
	

	/**
	 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
	 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
	 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
	 * cartão e alimentação
	 *
	 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
	 * Posto de combustivel (Fuel)
	 */
	@Override
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) {

		
		Card card = findCard(cardNumber);
		
		if (card.getType().getEstablishmentType() != establishmentType) {
			throw new IllegalArgumentException("O cartão não é compatível com o tipo do estabelecimento");
		}
		
		Double cashback = card.getType().calc(value);
		
		card.setBalance(card.getBalance() - cashback);
		
		cardRepository.save(card);

		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);
	}

	@Override
	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	@Override
	public Consumer createOrUpdateConsumer(Consumer consumer) {
		return repository.save(consumer);
	}

	@Override
	public Consumer updateConsumer(Consumer consumer) {
		return repository.findById(consumer.getId()).map(existingConsumer -> {
			
			existingConsumer.setBirthDate(consumer.getBirthDate());
			existingConsumer.setCity(consumer.getCity());
			existingConsumer.setCountry(consumer.getCountry());
			existingConsumer.setDocumentNumber(consumer.getDocumentNumber());
			existingConsumer.setEmail(consumer.getEmail());
			existingConsumer.setMobilePhoneNumber(consumer.getMobilePhoneNumber());
			existingConsumer.setName(consumer.getName());
			existingConsumer.setNumber(consumer.getNumber());
			existingConsumer.setPhoneNumber(consumer.getPhoneNumber());
			existingConsumer.setPortalCode(consumer.getPortalCode());
			existingConsumer.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
			existingConsumer.setStreet(consumer.getStreet());

			return existingConsumer;
		}).map(repository::save).orElseThrow(() -> new NotFoundException("Id não encontrado"));
	}
	
	private Card findCard(int cardNumber) {
		Card card = cardRepository.findOneByNumber(cardNumber);
		
		if (card == null) {
			throw new NotFoundException("Número de cartão não Encontrado");
		}
		return card;
	}
	
}