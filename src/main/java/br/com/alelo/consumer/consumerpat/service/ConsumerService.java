package br.com.alelo.consumer.consumerpat.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.exception.UnsupportedOperationException;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;

@Service
public class ConsumerService {
	
	private ConsumerRepository consumerRepository;
	private CardRepository cardRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
	
	@Autowired
	public ConsumerService(ConsumerRepository consumerRepository, CardRepository cardRepository) {
		this.consumerRepository = consumerRepository; 
		this.cardRepository = cardRepository;
	}
	
	/**
	 * Get a Consumer given an id
	 * 
	 * @param id
	 * @return consumer retrieved from database
	 */
	public Consumer findOne(long id) {
		try {
			Optional<Consumer> optionalConsumer = consumerRepository.findById(id);
			Consumer consumer = optionalConsumer.orElseThrow();
			consumer.getCards().stream().forEach(c -> c.setConsumer(consumer));
			return consumer;
		} catch(Exception e) {
			log.error("Error getting a consumer.", e);
			throw new UnsupportedOperationException("Error getting a consumer.", e);
		}
	}
	
	/**
	 * Get a paginated list of consumers.
	 * 
	 * @param offset start of pagination
	 * @param size of pagination
	 * @return
	 */
	public List<Consumer> getAllPaginated(int offset, int size) {
		log.debug("Executing: getAll()");
		
		Pageable pageable = PageRequest.of(offset, size);
		Page<Consumer> page = consumerRepository.findAll(pageable);
		if (page.hasContent()) {
			return page.getContent();
		}
		
		return Collections.emptyList();
	}

	/**
	 * Create a new consumer
	 * 
	 * @param consumer if cpf not null
	 * @return the consumer saved in database
	 */
	@Transactional
	public Consumer createOne(Consumer consumer) {
		log.debug("Executing: createOne()");
		try {
			final Consumer existentConsumer = consumerRepository.findByCpf(consumer.getCpf());
			
			if (existentConsumer != null) {  // poderia ser atualizado quando encontrasse o mesmo, depende da logica de negocio, do domínio da aplicacao
				log.error("Consumer already exists.");
				throw new UnsupportedOperationException("Error creating a consumer, operation aborted.");
			}
			
			// checar se os números de cartões já existem na base
			// só inserindo consumidor com cartões novos, removendo da lista cartões caso já existam.			
			final List<Card> insertingCards = consumer.getCards();
			final List<Card> existingCards = insertingCards.stream().filter(c -> cardRepository.findByNumber(c.getNumber()) != null).collect(Collectors.toList());
			insertingCards.removeAll(existingCards);
			insertingCards.stream().forEach(c -> c.setConsumer(consumer));

			return consumerRepository.save(consumer);
			
		} catch(Exception e) {
			log.error("Error creating a consumer.", e);
			throw new UnsupportedOperationException("Error creating a consumer, operation aborted.", e);
		}		
	}

	/**
	 * Update the consumer (Não deve ser possível alterar o saldo do cartão)
	 * 
	 * @param consumer with cpf not null
	 * @return the consumer updated
	 */
	@Transactional
	public Consumer updateOne(Consumer consumer) {
		log.debug("Executing: updateOne()");
		final Consumer updatedConsumer;
		try {
			updatedConsumer = consumerRepository.findByCpf(consumer.getCpf());
		
			if (updatedConsumer == null) { // estou apenas supondo que o mesmo pode ser inserido quando não encontrado numa atualização, tudo depende da logica de negocio, do domínio da aplicacao
				log.info("Consumer doesn't exists, inserting operation.");
				consumer.getCards().stream().forEach(c -> c.setConsumer(consumer));

				return consumerRepository.saveAndFlush(consumer);
			} 			

			final List<Card> updatedCards = updatedConsumer.getCards();
			final List<Card> newCards = consumer.getCards().stream().filter(c -> !updatedCards.contains(c)).collect(Collectors.toList());
			updatedCards.addAll(newCards);
			updatedCards.stream().forEach(c -> c.setConsumer(consumer));

			consumer.setCards(updatedCards);
			
			return consumerRepository.saveAndFlush(consumer);
			
		} catch(Exception e) {
			log.error("Error updating a consumer.");
			throw new UnsupportedOperationException("Error updating a consumer, operation aborted.");
		}
	}
	
}
