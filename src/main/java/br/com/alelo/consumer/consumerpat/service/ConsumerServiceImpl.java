package br.com.alelo.consumer.consumerpat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerExistException;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;

@Service
public class ConsumerServiceImpl implements IConsumerService{

	@Autowired
	private ConsumerRepository consumerRepository;
	
	public Consumer getConsumer(final Long consumerId) {

		Optional<Consumer> cardConsumer = consumerRepository.findById(consumerId);

		if(cardConsumer.isPresent()) {
			return cardConsumer.get();
		}else {
			throw new RestNotFoundException("Consumer not found");
		}
	}

	public List<ConsumerResponseDTO> getAllConsumersList(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Consumer> consumer = consumerRepository.findAll(paging);
		Page<ConsumerResponseDTO> dtoPage = consumer.map(new Function<Consumer, ConsumerResponseDTO>() {
		    @Override
		    public ConsumerResponseDTO apply(Consumer entity) {
		        return ConsumerConverter.toDTO(entity);
		    }
		});
		if (dtoPage.hasContent()) {
			return dtoPage.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Transactional
	public ConsumerResponseDTO createNewConsumer(ConsumerRequestDTO consumerRequestDTO) throws RestNotFoundException {

		if (consumerRepository.getConsumerByDocument(consumerRequestDTO.getDocumentNumber()).isPresent()) throw new ConsumerExistException("Consumer exist in database, try again");
		
		Consumer consumer = ConsumerConverter.toEntity(consumerRequestDTO);
		return  ConsumerConverter.toDTO(consumerRepository.save(consumer));
	}

	@Override
	public ConsumerResponseDTO updateConsumer(final Long id, final ConsumerRequestDTO consumerDTO) {

		Consumer findConsumer;
		Optional<Consumer> existsConsumer = consumerRepository.findById(id);
		if (existsConsumer.isPresent()) {
			findConsumer = existsConsumer.get();

			return  ConsumerConverter.toDTO(consumerRepository.save(ConsumerConverter.updateTarget(findConsumer, consumerDTO)));
		}else {
			throw new RestNotFoundException("Consumer not found");
		}
	}

}
