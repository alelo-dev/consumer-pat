package br.com.alelo.consumer.consumerpat.application.consumer.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.alelo.consumer.consumerpat.Mocks;
import br.com.alelo.consumer.consumerpat.application.consumer.search.SearchConsumerService;
import br.com.alelo.consumer.consumerpat.application.consumer.update.request.UpdateConsumerRequest;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.NotFoundException;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;

class UpdateConsumerServiceTest {

	@InjectMocks
	private UpdateConsumerService service = new UpdateConsumerService();

	@Mock
	private ConsumerRepository repository;

	@Mock
	private SearchConsumerService searchConsumerService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testUpdate() {
		Consumer consumer = Mocks.getConsumer();
		when(repository.findById(anyLong())).thenReturn(Optional.of(consumer));
		UpdateConsumerRequest request = Mocks.getUpdateConsumerRequest();
		service.update(request);
		verify(repository, times(1)).save(any());
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void updateInvalidConsumer() {
		try {
			service.update(new UpdateConsumerRequest());
		} catch (BusinessException ex) {
			assertEquals("error validating the consumer", ex.getMessage());
		}
	}

	@Test
	void updateClientNotFound() {
		try {

			when(repository.findById(anyLong())).thenReturn(Optional.empty());
			UpdateConsumerRequest request = Mocks.getUpdateConsumerRequest();
			service.update(request);

		} catch (NotFoundException ex) {
			assertEquals("customer not found", ex.getMessage());
		}
	}

	@Test
	void testCreateConsumerExistsDocumentNumber() {
		Consumer consumer = Mocks.getConsumer();
		when(repository.findById(anyLong())).thenReturn(Optional.of(consumer));
		when(searchConsumerService.existsConsumerByDocumentNumber(anyString())).thenReturn(true);
		try {
			UpdateConsumerRequest request = Mocks.getUpdateConsumerRequest();
			service.update(request);
		} catch (BusinessException ex) {
			assertEquals("there is another customer with this document number", ex.getMessage());
			verify(repository, never()).save(ArgumentMatchers.any());
		}
	}

}
