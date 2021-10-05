package br.com.alelo.consumer.consumerpat.api.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;

@Component
public class ConsumerMapper {

	@Autowired
	private ConsumerService consumerService;
	
	public ConsumerDTO entityToDto(Consumer consumer) {
		ConsumerDTO consumerDTO = new ConsumerDTO();
		BeanUtils.copyProperties(consumer, consumerDTO);
		return consumerDTO;
	}
	
	public Consumer dtoToEntity(Long id, ConsumerDTO consumerDTO) {
		Consumer consumer = consumerService.findById(id);

		if(consumerDTO.getAddress() == null) {
			BeanUtils.copyProperties(consumerDTO, consumer, "id", "cards", "address");
		}
		else {
			BeanUtils.copyProperties(consumerDTO, consumer, "id", "cards");
		}
		
		return consumer;
		
	}
	
	public Consumer dtoToEntity(ConsumerDTO consumerDTO) {
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(consumerDTO, consumer, "cards");
		return consumer;
	}

}
