package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;

@Service
public class ConsumerService implements IConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ExtractRepository extractRepository;

	/* Deve listar todos os clientes (cerca de 500) */
	@Override
	public List<Consumer> getAllConsumersList() {
		return consumerRepository.getAllConsumersList();
	}

	@Override
	public Consumer createConsumer(Consumer consumer) {
		return consumerRepository.save(consumer);
	}

	// Não deve ser possível alterar o saldo do cartão
	@Override
	public Consumer updateConsumer(Consumer consumer) {
		return consumerRepository.save(consumer);

	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@Override
	public Consumer setBalance(int cardNumber, double value) {
		Consumer consumer = null;
		consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			
		} else {
			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				// é cartão de refeição
	
			} else {
				// É cartão de combustivel
				consumer = consumerRepository.findByFuelCardNumber(cardNumber);
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);

			}
		}
		
		return consumerRepository.save(consumer);

	}

	

}
