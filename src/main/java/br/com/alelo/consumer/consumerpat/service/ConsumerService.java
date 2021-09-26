package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.service.dto.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.service.dto.response.ConsumerResponse;

public interface ConsumerService {

	ConsumerResponse save(ConsumerRequest request);
	
	List<ConsumerResponse> findAll();
	
	Optional<ConsumerResponse> findOne(Long id);
	
	ConsumerResponse update(Long id, ConsumerRequest request);
	
	void delete(Long id);
}
