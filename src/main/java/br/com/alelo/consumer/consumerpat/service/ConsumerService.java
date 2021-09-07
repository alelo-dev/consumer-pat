package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.constants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.filter.BuyFilter;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private ExtractService extractService;

	public List<Consumer> listConsumers() {
		return consumerRepository.findAll();
	}

	public Consumer salvar(Consumer consumer) {

		if (consumer.getId() != null) {
			if (!consumerRepository.findById(consumer.getId()).isEmpty()) {
				throw new DataIntegrityViolationException("consumidor.existente");
			}
		}
		if (consumer.getFoodCardNumber() != 0) {
			if (consumerRepository.findByFoodCardNumber(consumer.getFoodCardNumber()) != null) {
				throw new DataIntegrityViolationException("cartao.alimentacao.existente");
			}
		}
		if (consumer.getDrugstoreNumber() != 0) {
			if (consumerRepository.findByDrugstoreNumber(consumer.getDrugstoreNumber()) != null) {
				throw new DataIntegrityViolationException("cartao.farmacia.existente");
			}
		}
		if (consumer.getFuelCardNumber() != 0) {
			if (consumerRepository.findByFuelCardNumber(consumer.getFuelCardNumber()) != null) {
				throw new DataIntegrityViolationException("cartao.combustivel.existente");
			}
		}

		return consumerRepository.save(consumer);
	}

	public Consumer atualizar(Consumer consumer) {

		if (consumer.getId() == null) {
			throw new EmptyResultDataAccessException("consumidor.nao-encontrado", 1);
		}

		Consumer consumerUpdate = buscarConsumidorPorId(consumer.getId());

		return updateConsumer(consumerUpdate);
	}

	private Consumer updateConsumer(Consumer consumer) {

		String[] ignoreProperties = { "id", "foodCardBalance", "fuelCardBalance", "drugstoreCardBalance" };
		BeanUtils.copyProperties(consumer, consumer, ignoreProperties);
		return consumerRepository.save(consumer);

	}

	public Consumer buscarConsumidorPorId(Integer id) {
		Optional<Consumer> consumerOptional = consumerRepository.findById(id);

		if (consumerOptional == null || consumerOptional.isEmpty()) {
			throw new EmptyResultDataAccessException("consumidor.nao-encontrado", 1);
		}

		Consumer consumerSaved = consumerOptional.get();

		return consumerSaved;
	}

	public Consumer realizarCompra(BuyFilter buyFilter) {

		Consumer consumer = null;

		switch (buyFilter.getEstablishmentType()) {

		case 1: // Food
			consumer = buyFood(buyFilter);
			break;

		case 2: // Drugstore
			consumer = buyDrugstore(buyFilter);
			break;

		case 3: // Fuel
			consumer = buyFuel(buyFilter);
		}

		return consumer;

	}

	public Consumer setBalance(int cardNumber, double value) {

		Consumer consumer = null;

		if ((consumer = consumerRepository.findByDrugstoreNumber(cardNumber)) != null) {
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
		} else if ((consumer = consumerRepository.findByFoodCardNumber(cardNumber)) != null) {
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
		} else if ((consumer = consumerRepository.findByFuelCardNumber(cardNumber)) != null) {
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
		} else {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}
		return updateConsumer(consumer);

	}

	private Consumer buyFood(BuyFilter buyFilter) {

		Consumer consumer = consumerRepository.findByFoodCardNumber(buyFilter.getCardNumber());

		if (consumer == null) {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}

		Double value = buyFilter.getValue();
		value = roundDoubleValue(value - (value * .10));

		consumer.setFoodCardBalance(roundDoubleValue(consumer.getFoodCardBalance() - value));
		consumerRepository.save(consumer);
		extractService.saveExtract(buyFilter, consumer.getId(), EstablishmentType.FOOD.getId(), value);

		return consumer;
	}

	private Consumer buyDrugstore(BuyFilter buyFilter) {

		Consumer consumer = consumerRepository.findByDrugstoreNumber(buyFilter.getCardNumber());

		if (consumer == null) {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}

		Double value = roundDoubleValue(consumer.getDrugstoreCardBalance() - buyFilter.getValue());
		consumer.setDrugstoreCardBalance(value);
		consumerRepository.save(consumer);
		extractService.saveExtract(buyFilter, consumer.getId(), EstablishmentType.DRUGSTORE.getId(),
				buyFilter.getValue());

		return consumer;

	}

	private Consumer buyFuel(BuyFilter buyFilter) {

		Consumer consumer = consumerRepository.findByFuelCardNumber(buyFilter.getCardNumber());

		if (consumer == null) {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}

		Double value = buyFilter.getValue();
		value = roundDoubleValue(value * 1.35);

		consumer.setFuelCardBalance(roundDoubleValue(consumer.getFuelCardBalance() - value));
		consumerRepository.save(consumer);
		extractService.saveExtract(buyFilter, consumer.getId(), EstablishmentType.FUEL.getId(), value);

		return consumer;

	}

	private Double roundDoubleValue(Double value) {
		return Math.round(value * 100) / 100.0;
	}
}
