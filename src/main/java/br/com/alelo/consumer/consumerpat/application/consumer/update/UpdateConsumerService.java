package br.com.alelo.consumer.consumerpat.application.consumer.update;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.application.consumer.search.SearchConsumerService;
import br.com.alelo.consumer.consumerpat.application.consumer.update.request.UpdateConsumerRequest;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.NotFoundException;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;

@Service
public class UpdateConsumerService {

	@Autowired
	private ConsumerRepository repository;

	@Autowired
	private SearchConsumerService searchConsumerService;

	public void update(UpdateConsumerRequest request) {

		Consumer consumerUpdate = getConsumerByRequest(request);
		Consumer consumer = getClientById(request.getId());
		validateDocumentNumber(consumer.getDocumentNumber(), consumerUpdate.getDocumentNumber());
		BeanUtils.copyProperties(consumerUpdate, consumer, "id");
		repository.save(consumer);
	}

	private Consumer getClientById(Long id) {
		Optional<Consumer> findById = repository.findById(id);
		if (findById.isEmpty()) {
			throw new NotFoundException("customer not found");
		}
		return findById.get();
	}

	private Consumer getConsumerByRequest(UpdateConsumerRequest request) {
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(request, consumer);
		consumer.validated();
		return consumer;
	}

	private void validateDocumentNumber(String oldDocumentNumber, String documentNumber) {
		if (!oldDocumentNumber.equals(documentNumber)
				&& searchConsumerService.existsConsumerByDocumentNumber(documentNumber)) {
			throw new BusinessException("there is another customer with this document number");
		}
	}

}
