package br.com.alelo.consumer.consumerpat.application.consumer.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.application.consumer.search.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.application.consumer.search.response.ConsumerResponseList;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;

@Service
public class SearchConsumerService {

	@Autowired
	private ConsumerRepository repository;

	public boolean existsConsumerByDocumentNumber(String documentNumber) {

		Optional<Consumer> findByDocumentNumber = repository.findByDocumentNumber(documentNumber);

		return findByDocumentNumber.isPresent();

	}

	public ConsumerResponseList getConsumerList(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Consumer> consumers = repository.findAll(pageRequest);
		return buildResponseList(consumers);
	}

	private ConsumerResponseList buildResponseList(Page<Consumer> consumers) {
		List<ConsumerResponse> responseList = new ArrayList<>();

		if (!consumers.isEmpty()) {
			consumers.stream().forEach(consumer -> {
				ConsumerResponse response = new ConsumerResponse();
				BeanUtils.copyProperties(consumer, response);
				responseList.add(response);
			});
		}
		return new ConsumerResponseList(responseList);
	}
}
