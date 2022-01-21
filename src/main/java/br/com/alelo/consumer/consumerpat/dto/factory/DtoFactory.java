package br.com.alelo.consumer.consumerpat.dto.factory;

import org.springframework.beans.BeanUtils;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class DtoFactory {

	public static ConsumerDTO builder(Consumer entity) {
		ConsumerDTO dto = new ConsumerDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	
}
