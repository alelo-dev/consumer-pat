package br.com.alelo.consumer.consumerpat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.MapConsumerDto;
import br.com.alelo.consumer.consumerpat.error.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {

	@Mock
	private ConsumerRepository consumerRepository;	

	@Mock
	private MapConsumerDto mapConsumerDto;
	
	@InjectMocks
	ConsumerService consumerService;

	@Test
	void testUpdate() {
		Consumer consumer = new Consumer();
		
		ConsumerDto consumerDto = new ConsumerDto();
		
		Optional<Consumer> op = Optional.of(consumer);
		when(consumerRepository.findById(1L)).thenReturn(op);
		
		consumerService.update(1L, consumerDto);
		
		verify(mapConsumerDto, times(1)).update(consumerDto, consumer);
		verify(consumerRepository, times(1)).save(consumer);
	}
	
	@Test
	void testUpdateNotFound() {
				
		ConsumerDto consumerDto = new ConsumerDto();
		
		Optional<Consumer> op = Optional.empty();
		when(consumerRepository.findById(1L)).thenReturn(op);

		assertThrows(EntityNotFoundException.class, () -> consumerService.update(1L, consumerDto));
		
		
	}

}
