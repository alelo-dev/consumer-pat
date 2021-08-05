package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinesException;
import br.com.alelo.consumer.consumerpat.exception.MessageCode;
import br.com.alelo.consumer.consumerpat.exception.MessageResponse;
import br.com.alelo.consumer.consumerpat.model.Balance;
import br.com.alelo.consumer.consumerpat.model.Buy;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;

@Service
@Transactional(value = TxType.REQUIRED)
public class ConsumerService {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	public Consumer save(Consumer consumer) {
		this.validateSave(consumer.getId());
		this.validateRepeatedCardNumber(consumer);
		this.validateMultiples(0, consumer);
		this.validateBalance(consumer);
		return repository.save(consumer);
	}

	private void validateBalance(Consumer c) {
		if(c != null && ((c.getFoodCardBalance() > 0 && c.getFoodCardNumber() <= 0) 
				|| (c.getDrugstoreCardBalance() > 0 && c.getDrugstoreCardNumber() <= 0)
				|| (c.getFuelCardBalance() > 0 && c.getFuelCardNumber() <= 0))) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.CARD_NOT_INFORMED.getStatus());
			mr.setCode(MessageCode.CARD_NOT_INFORMED.getCode());
			mr.setMessage("Card not informed for balances");
			throw new BusinesException(mr);
		}
	}

	private void validateRepeatedCardNumber(Consumer c) {
		if (c != null
				&& ((c.getDrugstoreCardNumber() > 0 && c.getFoodCardNumber() > 0
						&& c.getDrugstoreCardNumber() == c.getFoodCardNumber()))
				|| (c.getDrugstoreCardNumber() > 0 && c.getFuelCardNumber() > 0
						&& c.getDrugstoreCardNumber() == c.getFuelCardNumber())
				|| (c.getFoodCardNumber() > 0 && c.getFuelCardNumber() > 0
						&& c.getFoodCardNumber() == c.getFuelCardNumber())) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.MULTIPLE_CARD_NUMBER.getStatus());
			mr.setCode(MessageCode.MULTIPLE_CARD_NUMBER.getCode());
			mr.setMessage("Card number repeated not permited");
			throw new BusinesException(mr);
		}
	}

	public Consumer update(Consumer consumer) {

		this.validateUpdate(consumer.getId());
		this.validateRepeatedCardNumber(consumer);
		this.validateMultiples(consumer.getId(), consumer);
		this.validateBalance(consumer);
		repository.update(consumer.getId(), consumer.getName(), consumer.getDocumentNumber(), consumer.getBirthDate(),
				consumer.getMobilePhoneNumber(), consumer.getResidencePhoneNumber(), consumer.getPhoneNumber(),
				consumer.getEmail(), consumer.getStreet(), consumer.getNumber(), consumer.getCity(),
				consumer.getCountry(), consumer.getPortalCode(), consumer.getFoodCardNumber(),
				consumer.getFuelCardNumber(), consumer.getDrugstoreCardNumber());
		return consumer;
	}

	private void validateUpdate(Integer id) {

		Consumer consumer = repository.findByIdConsumer(id);
		if (consumer == null) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.CONSUMER_NOT_FOUND.getStatus());
			mr.setCode(MessageCode.CONSUMER_NOT_FOUND.getCode());
			mr.setMessage("Consumer not found [id=" + id + "]");
			throw new BusinesException(mr);
		}
	}

	private void validateSave(Integer id) {
		if (id != null) {
			Consumer consumer = repository.findByIdConsumer(id);
			if (consumer != null) {
				MessageResponse mr = new MessageResponse();
				mr.setStatus(MessageCode.CONSUMER_ALREADY_EXISTS.getStatus());
				mr.setCode(MessageCode.CONSUMER_ALREADY_EXISTS.getCode());
				mr.setMessage("Consumer already exists [id=" + id + "]");
				throw new BusinesException(mr);
			}
		}
	}

	public Consumer setBalance(Balance balance) {
		this.validateCardNumber(balance.getCardNumber());
		this.validateMultiplesCards(balance.getCardNumber());
		this.validateValue(balance.getValue());

		int cardNumber = balance.getCardNumber();
		double value = balance.getValue();
		Consumer consumer = null;

		consumer = repository.findByDrugstoreCardNumber(cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			return repository.save(consumer);
		} else {
			consumer = repository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				return repository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = repository.findByFuelCardNumber(cardNumber);
				if (consumer != null) {
					consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
					return repository.save(consumer);
				}
			}
		}
		MessageResponse mr = new MessageResponse();
		mr.setStatus(MessageCode.CARD_NOT_FOUND.getStatus());
		mr.setCode(MessageCode.CARD_NOT_FOUND.getCode());
		mr.setMessage("Card number not found [number=" + cardNumber + "]");
		throw new BusinesException(mr);
	}

	private void validateValue(double value) {
		if (value <= 0) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.INVALID_VALUE.getStatus());
			mr.setCode(MessageCode.INVALID_VALUE.getCode());
			mr.setMessage("Value must be greater than zero [value=" + value + "]");
			throw new BusinesException(mr);
		}
	}

	private void validateCardNumber(int value) {
		if (value <= 0) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.INVALID_VALUE.getStatus());
			mr.setCode(MessageCode.INVALID_VALUE.getCode());
			mr.setMessage("Invalid card number [card number=" + value + "]");
			throw new BusinesException(mr);
		}
	}

	public Extract buy(Buy buy) {
		this.validateCardNumber(buy.getCardNumber());
		this.validateMultiplesCards(buy.getCardNumber());
		this.validateValue(buy.getValue());

		MessageResponse mr1 = new MessageResponse();
		mr1.setStatus(MessageCode.INSUFFICIENT_FUNDS.getStatus());
		mr1.setCode(MessageCode.INSUFFICIENT_FUNDS.getCode());
		mr1.setMessage("Insufficient funds [number=" + buy.getCardNumber() + "]");

		int establishmentType = buy.getEstablishmentType();
		String establishmentName = buy.getEstablishmentName();
		int cardNumber = buy.getCardNumber();
		String productDescription = buy.getProductDescription();
		double value = buy.getValue();

		Consumer consumer = null;

		if (establishmentType == 1) {
			Double cashback = (value / 100) * 10;
			value = value - cashback;

			consumer = repository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				if (consumer.getFoodCardBalance() >= value) {
					consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
					repository.save(consumer);
				} else {
					throw new BusinesException(mr1);
				}
			}
		} else if (establishmentType == 2) {
			consumer = repository.findByDrugstoreCardNumber(cardNumber);
			if (consumer != null) {
				if (consumer.getDrugstoreCardBalance() >= value) {
					consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
					repository.save(consumer);
				} else {
					throw new BusinesException(mr1);
				}
			}
		} else {
			Double tax = (value / 100) * 35;
			value = value + tax;

			consumer = repository.findByFuelCardNumber(cardNumber);
			if (consumer != null) {
				if (consumer.getFuelCardBalance() >= value) {
					consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
					repository.save(consumer);
				} else {
					throw new BusinesException(mr1);
				}
			}
		}
		if (consumer != null) {
			Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
			return extractRepository.save(extract);
		}
		MessageResponse mr = new MessageResponse();
		mr.setStatus(MessageCode.CARD_NOT_FOUND.getStatus());
		mr.setCode(MessageCode.CARD_NOT_FOUND.getCode());
		mr.setMessage("Card number not found [number=" + cardNumber + "]");
		throw new BusinesException(mr);
	}

	private void validateMultiples(int id, Consumer consumer) {
		List<Consumer> c = repository.findByCardNumber(id, consumer.getFoodCardNumber(),
				consumer.getDrugstoreCardNumber(), consumer.getFuelCardNumber());
		if (c.size() > 0) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.MULTIPLE_CARD_NUMBER.getStatus());
			mr.setCode(MessageCode.MULTIPLE_CARD_NUMBER.getCode());
			mr.setMessage("Card number already registered for another consumer [number=" + consumer + "]");
			throw new BusinesException(mr);
		}
	}

	private void validateMultiplesCards(int cardNumber) {
		List<Consumer> c = repository.validateCardNumber(cardNumber);
		if (c.size() > 1) {
			MessageResponse mr = new MessageResponse();
			mr.setStatus(MessageCode.MULTIPLE_CARD_NUMBER.getStatus());
			mr.setCode(MessageCode.MULTIPLE_CARD_NUMBER.getCode());
			mr.setMessage("Card number already registered for another consumer [number=" + cardNumber + "]");
			throw new BusinesException(mr);
		}
	}
}
