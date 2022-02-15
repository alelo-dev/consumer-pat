package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private CardRepository cardRepositoy;
	
	public Page<Consumer> getAllConsumersListWhithPagination(Pageable pageable){
		return consumerRepository.findAll(pageable);
	}	
	
	@Transactional
	public Consumer save(Consumer consumer) {
		
		Consumer consumerBanco = consumerRepository.save(consumer);
		
		if (consumer.getCards() != null) {
		
			consumer.getCards().forEach( 
					cardAux -> {
						cardAux.setConsumer(consumerBanco);
						cardRepositoy.save(cardAux);						
					});
		}		
		
		
		return consumerBanco;
	}
	
	public Consumer updateConsumer(Integer id, Consumer consumer) {

		Consumer lConsumer = findById(id);		

		BeanUtils.copyProperties(consumer, lConsumer, "id");
		
		lConsumer = consumerRepository.save(lConsumer);
		
		return lConsumer;
		
	}
	
	public Consumer findById(Integer codigo) {
		
		Optional<Consumer> optional = consumerRepository.findById(codigo);
		
		return optional.orElseThrow(() ->
			new EmptyResultDataAccessException(1)
		);
	}
}
