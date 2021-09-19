package br.com.alelo.consumer.consumerpat.application.consumer.create;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.alelo.consumer.consumerpat.Mocks;
import br.com.alelo.consumer.consumerpat.application.consumer.search.SearchConsumerService;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;

class CreateConsumerServiceTest {

	@InjectMocks
	private CreateConsumerService service = new CreateConsumerService();

	@Mock
	private ConsumerRepository repository;

	@Mock
	private SearchConsumerService searchService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateConsumer() {
		when(searchService.existsConsumerByDocumentNumber(anyString())).thenReturn(false);
		service.createConsumer(Mocks.getCreateConsumerRequest());
		verify(repository, times(1)).save(ArgumentMatchers.any());
	}

	@Test
	void testCreateConsumerExistsDocumentNumber() {
		when(searchService.existsConsumerByDocumentNumber(anyString())).thenReturn(true);
		try {
			service.createConsumer(Mocks.getCreateConsumerRequest());
		} catch (BusinessException ex) {
			assertEquals("there is another customer with this document number", ex.getMessage());
			verify(repository, never()).save(ArgumentMatchers.any());
		}
	}

}
