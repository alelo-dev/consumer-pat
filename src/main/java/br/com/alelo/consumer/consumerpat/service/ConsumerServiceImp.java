package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
@Transactional
public class ConsumerServiceImp implements ConsumerService {
	@Autowired
	public ConsumerRepository repository;
	@Autowired
	public ExtractRepository extractRepository;

	public ConsumerServiceImp() {

	}

	@Override
	public void setBalance(int cardNumber, double value) {
		Consumer consumer = null;
		consumer = repository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			repository.save(consumer);
		} else {
			consumer = repository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				repository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = repository.findByFuelCardNumber(cardNumber);
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				repository.save(consumer);
			}
		}
	}

	@Override
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) {
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

			consumer = repository.findByFoodCardNumber(cardNumber);
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			repository.save(consumer);

		} else if (establishmentType == 2) {
			consumer = repository.findByDrugstoreNumber(cardNumber);
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
			repository.save(consumer);

		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (value / 100) * 35;
			value = value + tax;

			consumer = repository.findByFuelCardNumber(cardNumber);
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			repository.save(consumer);
		}

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
			existingConsumer.setDrugstoreNumber(consumer.getDrugstoreNumber());
			existingConsumer.setEmail(consumer.getEmail());
			existingConsumer.setFoodCardNumber(consumer.getFoodCardNumber());
			existingConsumer.setFuelCardNumber(consumer.getFuelCardNumber());
			existingConsumer.setMobilePhoneNumber(consumer.getMobilePhoneNumber());
			existingConsumer.setName(consumer.getName());
			existingConsumer.setNumber(consumer.getNumber());
			existingConsumer.setPhoneNumber(consumer.getPhoneNumber());
			existingConsumer.setPortalCode(consumer.getPortalCode());
			existingConsumer.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
			existingConsumer.setStreet(consumer.getStreet());

			return existingConsumer;
		}).map(repository::save).get();
	}
}