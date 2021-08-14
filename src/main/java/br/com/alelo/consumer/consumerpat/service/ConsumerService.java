package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {
	
	@Autowired
	ConsumerRepository repository;

	/**
	 * @param pageRequest
	 * @return
	 */
	public List<Consumer> findAll(PageRequest pageRequest){
		Page<Consumer> recordsPage = repository.findAll(pageRequest);
	    return recordsPage.getContent();
	}
}