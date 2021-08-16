package br.com.alelo.consumer.consumerpat.service.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.common.CopyProperties;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;
	
	/**
	 * Recupera o cliente por Id.
	 * 
	 * @param id
	 * @return ConsumerDTO
	 */
	public ConsumerDTO getConsumer(Long id){		
		return  CopyProperties.consumerToDto(consumerRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("Consumidor não encontrado!")));
		
	}
	
	/**
	 * Recupera uma lista de clientes.
	 * 
	 * @returnPage<ConsumerDTO>
	 */
	public Page<ConsumerDTO> getConsumers(Pageable pageable){	
		var page = consumerRepository.findAll(pageable);		
		return page.map(this::convertToConsumerDTO);		 
	}	
	
	/**
	 * Salva cliente.
	 * 
	 * @param ConsumerDTO
	 */
	public ConsumerDTO saveConsumer(ConsumerDTO consumerDTO) {	
		Consumer consumer = CopyProperties.dtoToConsumer(consumerDTO);		
		return CopyProperties.consumerToDto(consumerRepository.save(consumer));
	}
	
	/**
	 * Atualiza cliente.
	 * 
	 * @param ConsumerDTO
	 */
	public ConsumerDTO updateConsumer(ConsumerDTO consumerDTO) {
		ConsumerDTO consumerAtual =getConsumer(consumerDTO.getId());		
		keepBalance(consumerAtual,consumerDTO);			
		return CopyProperties.consumerToDto(consumerRepository.save(CopyProperties.dtoToConsumer(consumerDTO)));		
	}
	
	private ConsumerDTO convertToConsumerDTO(Consumer consumer) {
		return CopyProperties.consumerToDto(consumer);
	}
	
	private void keepBalance(ConsumerDTO consumerAtual, ConsumerDTO consumerDTO) {
		consumerDTO.getCards().forEach(novo -> 
			consumerAtual.getCards().forEach(atual -> {
				if(novo.getId().equals(atual.getId())) {
					novo.setBalance(atual.getBalance());
				} 
			})
		);		
	}
}
