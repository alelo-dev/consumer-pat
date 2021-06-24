package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.DocumentoNumberAlreadyRegisteredException;
import br.com.alelo.consumer.consumerpat.payload.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.payload.ConsumerUpdateRequest;
import br.com.alelo.consumer.consumerpat.payload.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsumerService {

	private final ConsumerRepository consumerRepository;

	public ConsumerResponse findConsumerDTOById(final Long id){ 
		final Consumer consumer = this.consumerRepository.findById(id).orElseThrow(() -> new ConsumerNotFoundException());
		return ConsumerConverter.toResponse(consumer);
	}

	public PageImpl<ConsumerResponse> findAll(final Pageable pageable){
		final Page<Consumer> pageConsumer = this.consumerRepository.findAll(pageable);
		return new PageImpl<>(ConsumerConverter.toListConsumerConsumerResponse(pageConsumer.getContent()));
	}

	public void save(final ConsumerRequest request){
		if(consumerRepository.existsByDocumentNumber(request.getDocumentNumber())) {
			throw new DocumentoNumberAlreadyRegisteredException();
		}
		
		Consumer consumer = ConsumerConverter.toEntity(request);
		consumer.getConsumerCards().forEach(f -> f.setConsumer(consumer));
		this.consumerRepository.saveAndFlush(consumer);
	}

	public void update(final Long id, final ConsumerUpdateRequest request){
		final Consumer consumer = this.findById(id);
		final Consumer consumerConverted = ConsumerConverter.toEntity(request);
		consumer.merge(consumerConverted);
		this.consumerRepository.saveAndFlush(consumer);
	}

	public void delete(final Long id){
		final Consumer consumer = this.findById(id);
		this.consumerRepository.delete(consumer);
	}

	private Consumer findById(final Long id){
		return this.consumerRepository.findById(id).orElseThrow(() -> new ConsumerNotFoundException());
	}

}