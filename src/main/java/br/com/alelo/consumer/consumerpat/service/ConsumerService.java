package br.com.alelo.consumer.consumerpat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {
	@Autowired
    private ConsumerRepository consumerRepository;
	
	@Autowired 
	private AddressRepository addressRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	public List<ConsumerDto> findAll() {
		List<ConsumerDto> dtos = new ArrayList<ConsumerDto>();
		List<Consumer> consumers = this.consumerRepository.findAll();
		if (consumers != null && consumers.size() > 0) {
			for (Consumer consumer : consumers) {
				ConsumerDto dto = ConsumerDto.fromConsumer(consumer);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
	public void save(ConsumerDto consumerdto) {
		Consumer consumer = consumerdto.toConsumer();
		
		Consumer saved = this.consumerRepository.save(consumer);
		if (consumer.getAddresses() != null && consumer.getAddresses().size() > 0) {
			for (Address address : consumer.getAddresses()) {
				address.setConsumer(saved);
				this.addressRepository.save(address);
			}
		}
		
		if (consumer.getCards() != null && consumer.getCards().size() > 0) {
			for (Card card : consumer.getCards()) {
				card.setConsumer(saved);
				this.cardRepository.save(card);
			}
		}
	}
	
	public void delete(int id) {
		if (id > 0 && this.consumerRepository.existsById(id)) {
			this.consumerRepository.deleteById(id);
		}
	}
}
