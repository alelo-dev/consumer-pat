package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface IConsumerService {
	public Consumer getConsumer(final Long consumerId);
	public List<ConsumerResponseDTO> getAllConsumersList(Integer pageNo, Integer pageSize, String sortBy);
	public ConsumerResponseDTO createNewConsumer(ConsumerRequestDTO consumer) throws Exception;
	public ConsumerResponseDTO updateConsumer(final Long id, final ConsumerRequestDTO consumerDTO);
}
