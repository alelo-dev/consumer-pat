package br.com.alelo.consumer.consumerpat.application.consumer.create;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.application.consumer.create.request.CreateConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.consumer.search.SearchConsumerService;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;

@Service
public class CreateConsumerService {

	@Autowired
	private ConsumerRepository repository;

	@Autowired
	private SearchConsumerService searchConsumerService;

	public void createConsumer(CreateConsumerRequest request) {

		Consumer consumer = getConsumerByRequest(request);
		consumer.validated();
		validateExistenceDocumentNumber(request.getDocumentNumber());
		createCards(consumer);
		repository.save(consumer);

	}

	private void createCards(Consumer consumer) {
		for (CardType cardType : CardType.values()) {
			Card card = new Card();
			card.setConsumer(consumer);
			card.setType(cardType);
			card.setBalance(BigDecimal.ZERO);
			consumer.addCard(card);
		}
	}

	private void validateExistenceDocumentNumber(String documentNumber) {
		if (searchConsumerService.existsConsumerByDocumentNumber(documentNumber)) {
			throw new BusinessException("there is another customer with this document number");
		}
	}

	private Consumer getConsumerByRequest(CreateConsumerRequest request) {
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(request, consumer);
		return consumer;
	}

}
