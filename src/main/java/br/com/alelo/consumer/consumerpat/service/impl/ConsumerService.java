package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
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
	public void createConsumer(Consumer consumer) {
		consumerRepository.save(consumer);
	}

	// Não deve ser possível alterar o saldo do cartão
	@Override
	public void updateConsumer(Consumer consumer) {
		consumerRepository.save(consumer);

	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@Override
	public void setBalance(int cardNumber, double value) {
		Consumer consumer = null;
		consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			consumerRepository.save(consumer);
		} else {
			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				consumerRepository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = consumerRepository.findByFuelCardNumber(cardNumber);
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				consumerRepository.save(consumer);
			}
		}

	}

	@Override
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
		Consumer consumer = null;
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */

		if (establishmentType == 1) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback = (value / 100) * 10;
			value = value - cashback;

			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			consumerRepository.save(consumer);

		} else if (establishmentType == 2) {
			consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
			consumerRepository.save(consumer);

		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (value / 100) * 35;
			value = value + tax;

			consumer = consumerRepository.findByFuelCardNumber(cardNumber);
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			consumerRepository.save(consumer);
		}

		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);
	}

}
