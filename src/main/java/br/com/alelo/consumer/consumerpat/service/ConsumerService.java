package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.controller.CartaoCombustivel;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.card.CardDrugStore;
import br.com.alelo.consumer.consumerpat.entity.card.CardFood;
import br.com.alelo.consumer.consumerpat.entity.establishment.Establishment;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	CardService cardService;

	public List<Consumer> findAll() {
		return consumerRepository.findAll();
	}

	public void save(Consumer consumer) {
		consumerRepository.save(consumer);
	}

	public void put(Consumer consumer) {
		Optional<Consumer> consumerData = consumerRepository.findById(consumer.getId());
		if (balanceVerify(consumerData.get(), consumer)){
			consumerRepository.save(consumer);
		}
	}
	
	private boolean balanceVerify(Consumer oldConsumer, Consumer newConsumer) {
		if (oldConsumer.getFoodCardBalance() != newConsumer.getFoodCardBalance()
				|| oldConsumer.getDrugstoreCardBalance() != newConsumer.getDrugstoreCardBalance()
				|| oldConsumer.getFuelCardBalance() != newConsumer.getFuelCardBalance())  {
			return false;
		}
		return true;
	}
	
	public void setBalance(Consumer consumer, int cardNumber, double value) {
		 consumer = cardService.executa(
	        		new CardDrugStore(
	        		new CardFood(
	        		 new CartaoCombustivel(null))), cardNumber, value);
		 consumerRepository.save(consumer);
	}

	public double buy(int establishmentType, double value, int cardNumber, Consumer consumer) {
		 Establishment establishment = Establishment.searchEstablishment(establishmentType);
	     value = establishment.discountCalculator(value);
	     consumer = establishment.setCard(cardNumber, value);
	     consumerRepository.save(consumer);
		 return value;
	}

}
