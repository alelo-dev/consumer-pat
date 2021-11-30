package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.DrugstoreCard;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.FoodCard;
import br.com.alelo.consumer.consumerpat.entity.FuelCard;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

public class ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;
	
    @Autowired
    ExtractRepository extractRepository;

	public Consumer create(Consumer consumer) {
		return consumerRepository.save(consumer);
	}

	public List<Consumer> list() {
		return consumerRepository.findAll();
	}

	public Consumer update(Consumer consumer) {
		Optional<Consumer> returnedConsumer = consumerRepository.findById(consumer.getId());
		Consumer oldConsumer = returnedConsumer.get();
		if (oldConsumer == null) {
			throw new NotFoundException("update", "id", String.valueOf(consumer.getId()));
		}
		if (oldConsumer.getDrugstoreCardBalance() != consumer.getDrugstoreCardBalance()
				|| oldConsumer.getFoodCardBalance() != consumer.getFoodCardBalance()
				|| oldConsumer.getFuelCardBalance() != consumer.getFuelCardBalance()) {
			throw new BusinessException("Nao e permitida alteracao de saldo de cartao por este servico", "cardBalance",
					"");
		}
		return consumerRepository.save(consumer);
	}

	public boolean updateBalance(int cardNumber, double value) {

		Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
		if (consumer != null) {
			// drugstore
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			consumerRepository.save(consumer);
			return true;
		}
		consumer = consumerRepository.findByFoodCardNumber(cardNumber);
		if (consumer != null) {
			// food
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
			consumerRepository.save(consumer);
			return true;
		}
		consumer = consumerRepository.findByFuelCardNumber(cardNumber);
		if (consumer != null) {
			// fuel
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
			consumerRepository.save(consumer);
			return true;
		}
		throw new NotFoundException("set-card-balance", "cardNumber", String.valueOf(cardNumber));
	}

	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) {

		/*
		 * O valores so podem ser debitados dos cartoes com os tipos correspondentes ao
		 * tipo do estabelecimento da compra.
		 *
		 * Tipos de estabelcimentos 1 - Alimentacao (food) 2 - Farmacia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */
		Consumer consumer = null;
		switch (establishmentType) {
		case 1: {
			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			if (consumer == null) {
				throw new NotFoundException("buy", "cardNumber", String.valueOf(cardNumber));
			}
			consumer.setFoodCardBalance(new FoodCard().calculate(consumer.getFoodCardBalance(), value));
		}
			break;
		case 2: {
			consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
			if (consumer == null) {
				throw new NotFoundException("buy", "cardNumber", String.valueOf(cardNumber));
			}
			consumer.setDrugstoreCardBalance(new DrugstoreCard().calculate(consumer.getDrugstoreCardBalance(), value));
		}
			break;
		case 3: {
			consumer = consumerRepository.findByFuelCardNumber(cardNumber);
			if (consumer == null) {
				throw new NotFoundException("buy", "cardNumber", String.valueOf(cardNumber));
			}
			consumer.setFuelCardBalance(new FuelCard().calculate(consumer.getFuelCardBalance(), value));
		}
			break;
		default: {
			throw new BusinessException("Tipo de Estabelecimento Invalido", "establishmentType",
					String.valueOf(establishmentType));
		}
		}
		consumerRepository.save(consumer);

		 Extract extract = new Extract(establishmentName, productDescription, new
		 Date(), cardNumber, value);
		 extractRepository.save(extract);

	}
}
