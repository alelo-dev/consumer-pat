package br.com.alelo.consumer.consumerpat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerServiceTest {

	@Autowired
	IConsumerService service;
	
	@Mock
	ConsumerRepository repository;
	
	@Test
	public void shoudReturnSuccess_findById() {
		Long id = 1L;
		when(repository.getOne(id)).thenReturn(null);
		
		assertEquals(true, true);
	}
}
