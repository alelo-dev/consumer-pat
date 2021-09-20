package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ExtractRepository extractRepository;

	public Page<Consumer> findAllConsumer(Pageable paginacao) {
		return consumerRepository.getAllConsumersList(paginacao);
	}

	public Boolean saveConsumer(Consumer consumer) {
		try {
			consumerRepository.save(consumer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void updateConsumer(Consumer consumer) {

		this.updateFields(consumer);

	}

	private Consumer consultar(int id) {
		Optional<Consumer> consumerOptional = this.consumerRepository.findById(id);
		if (consumerOptional.isPresent()) {
			return consumerOptional.get();
		}
		return null;
	}

	private void updateFields(Consumer consumer) {

		Consumer _consumer = this.consultar(consumer.getId());

		// Consumer
		_consumer.setId(consumer.getId());
		_consumer.setName(consumer.getName());
		_consumer.setDocumentNumber(consumer.getDocumentNumber());
		_consumer.setBirthDate(consumer.getBirthDate());

		// Address
		_consumer.getAddress().setNumber(consumer.getAddress().getNumber());
		_consumer.getAddress().setStreet(consumer.getAddress().getStreet());
		_consumer.getAddress().setCity(consumer.getAddress().getCity());
		_consumer.getAddress().setCountry(consumer.getAddress().getCountry());
		_consumer.getAddress().setPortalCode(consumer.getAddress().getPortalCode());

		// Contacts

		_consumer.getContacts().setMobilePhoneNumber(consumer.getContacts().getMobilePhoneNumber());
		_consumer.getContacts().setResidencePhoneNumber(consumer.getContacts().getResidencePhoneNumber());
		_consumer.getContacts().setPhoneNumber(consumer.getContacts().getPhoneNumber());
		_consumer.getContacts().setEmail(consumer.getContacts().getEmail());

		// cards
		_consumer.setFoodCardNumber(consumer.getFoodCardNumber());
		_consumer.setFoodCardBalance(_consumer.getFoodCardBalance());
		_consumer.setFuelCardNumber(consumer.getFuelCardNumber());
		_consumer.setFuelCardBalance(_consumer.getFuelCardBalance());

		this.consumerRepository.save(_consumer);

	}

	public void addMoneyCard(int cardNumber, double value) {

		Consumer consumer = null;

		// Farmácia
		consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
		if (consumer != null) {
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
		} else {
			// Refeição
			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
			} else {
				// Combustível
				consumer = consumerRepository.findByFuelCardNumber(cardNumber);
				if (consumer != null) {
					consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				}
			}
		}
		this.updateBalance(consumer);
	}

	public void byCard(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) {

		Consumer consumer = null;

		if (establishmentType == 1) {
			Double cashback = (value / 100) * 10;
			value = value - cashback;
			consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
 
		} else if (establishmentType == 2) {
			consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);

		} else {// Nas compras com o cartão de combustivel existe um acrescimo de 35%;

			Double tax = (value / 100) * 35;
			value = value + tax;

			consumer = consumerRepository.findByFuelCardNumber(cardNumber);
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
		}

		consumerRepository.save(consumer);

		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extractRepository.save(extract);

	}

	private void updateBalance(Consumer _consumer) {
		consumerRepository.save(_consumer);
	}

}
