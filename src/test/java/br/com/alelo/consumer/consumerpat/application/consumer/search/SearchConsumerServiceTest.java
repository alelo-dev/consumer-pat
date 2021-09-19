package br.com.alelo.consumer.consumerpat.application.consumer.search;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alelo.consumer.consumerpat.Mocks;
import br.com.alelo.consumer.consumerpat.application.consumer.search.response.ConsumerResponseList;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;

class SearchConsumerServiceTest {

	@InjectMocks
	private SearchConsumerService service = new SearchConsumerService();

	@Mock
	private ConsumerRepository repository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testExistsConsumerByDocumentNumber() {
		Consumer consumer = Mocks.getConsumer();
		when(repository.findByDocumentNumber(ArgumentMatchers.anyString())).thenReturn(Optional.of(consumer));
		boolean existsConsumerByDocumentNumber = service.existsConsumerByDocumentNumber("1");
		assertTrue(existsConsumerByDocumentNumber);
	}

	@Test
	void testGetConsumerList() {

		List<Consumer> consumers = new ArrayList<>();
		consumers.add(Mocks.getConsumer());
		Page<Consumer> values = new PageImpl<Consumer>(consumers);
		when(repository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(values);

		ConsumerResponseList consumerList = service.getConsumerList(1, 1);

		assertFalse(consumerList.getConsumerList().isEmpty());

	}

}
