package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository repository;

	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	public String createConsumer(Consumer consumer) {
		repository.save(consumer);
		return "Consumer criado com sucesso";
	}

	public String updateConsumer(Consumer consumer) {
		String response;
		boolean exists = repository.existsById(consumer.getId()); 
		Optional<Consumer> after = repository.findById(consumer.getId());
		if (exists) {
			if (consumer.getFoodCardBalance() > 0 || consumer.getDrugstoreCardBalance() > 0
					|| consumer.getFuelCardBalance() > 0) {
				response = "Nao e possivel alterar o saldo do cartao.";
			} else {
				consumer.setDrugstoreCardBalance(after.get().getDrugstoreCardBalance());
				consumer.setFoodCardBalance(after.get().getFoodCardBalance());
				consumer.setFuelCardBalance(after.get().getFuelCardBalance());
				repository.save(consumer);
				response = "Consumer atualizado com sucesso.";
			}
		} else {
			response = "Consumer com este ID nao existe, para adicionar um Consumer tente o metodo proprio para isso: createConsumer.";
		}
		return response;
	}

	public String setBalance(int cardNumber, double value) {
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
		return "Saldo do cartão atualizado com sucesso.";
	}
}
