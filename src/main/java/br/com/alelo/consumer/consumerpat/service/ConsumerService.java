package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.respository.filter.BuyFilter;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private ExtractRepository extractRepository;

	public List<Consumer> listConsumers() {
		return consumerRepository.findAll();
	}

	public Consumer salvar(Consumer consumer) {

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
		Consumer consumerSaved = buscarConsumidorPorId(consumer.getId());

		BeanUtils.copyProperties(consumer, consumerSaved, "id");
		return consumerRepository.save(consumerSaved);
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

	private Consumer buyFood(BuyFilter buyFilter) {

		Consumer consumer = consumerRepository.findByFoodCardNumber(buyFilter.getCardNumber());

		if (consumer == null) {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}

		Double value = buyFilter.getValue();
		value = (value - (value * .10));

		consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
		consumerRepository.save(consumer);
		saveExtract(buyFilter, consumer.getId(), value);

		return consumer;
	}

	private Consumer buyDrugstore(BuyFilter buyFilter) {

		Consumer consumer = consumerRepository.findByDrugstoreNumber(buyFilter.getCardNumber());

		if (consumer == null) {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}

		Double value = consumer.getDrugstoreCardBalance() - buyFilter.getValue();
		consumer.setDrugstoreCardBalance(value);
		consumerRepository.save(consumer);
		saveExtract(buyFilter, consumer.getId(), value);

		return consumer;

	}

	private Consumer buyFuel(BuyFilter buyFilter) {

		Consumer consumer = consumerRepository.findByFuelCardNumber(buyFilter.getCardNumber());

		if (consumer == null) {
			throw new EmptyResultDataAccessException("cartao.nao-encontrado", 1);
		}

		Double value = buyFilter.getValue();
		value = (value * 1.35);

		consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
		consumerRepository.save(consumer);
		saveExtract(buyFilter, consumer.getId(), value);

		return consumer;

	}

	private void saveExtract(BuyFilter buyFilter, int id, Double value) {

		Extract extract = new Extract(buyFilter.getEstablishmentName(), buyFilter.getProductDescription(), new Date(),
				buyFilter.getCardNumber(), value);

		extractRepository.save(extract);

	}

}
