package br.com.alelo.consumer.consumerpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ObjectNotFoundException;
import br.com.alelo.consumer.consumerpat.mock.dto.ConsumerCreateDTOMock;
import br.com.alelo.consumer.consumerpat.mock.entity.ConsumerMock;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerServiceTest {

	@Autowired
	private IConsumerService service;
	
	@MockBean
	private ConsumerRepository consumerRepository;
	
	@Test
	public void whenFindById_thenReturnSuccess() {
		Long id = 1L;
		when(consumerRepository.findById(id)).thenReturn(Optional.of(ConsumerMock.created()));
		
		ConsumerDTO consumerDTO = service.findById(id);
		assertEquals(consumerDTO.getId(), id);
		assertEquals(consumerDTO.getName(), "Joao");
		assertEquals(consumerDTO.getDocumentNumber(), 112233);
		assertEquals(consumerDTO.getBirthDate(), LocalDate.of(1992, 12, 31));
		assertEquals(consumerDTO.getAddress().size(), 1);
		assertEquals(consumerDTO.getCards().size(), 3);
		assertEquals(consumerDTO.getContacts().size(), 3);
		verify(consumerRepository, times(1)).findById(id);
	}
	
	@Test
	public void whenFindById_thenReturnFailedObjectNotFoundException() {
		Long id = 1L;
		when(consumerRepository.findById(id)).thenReturn(Optional.empty());
		try {
			service.findById(id);
            fail("Espera-se um ObjectNotFoundException, pois não se retorna nenhum item para o Id selecionado.");
        } catch (ObjectNotFoundException e) {
    		verify(consumerRepository, times(1)).findById(id);
            assertThat(e).hasMessage("Objeto não foi encontrado! ID:" + id + ", class:" + Consumer.class.getName());
        }
	}
	
	@Test
	public void whenCreate_thenReturnSuccess() {
		ConsumerCreateDTO consumerCreateDTO = ConsumerCreateDTOMock.create();
		Consumer consumer = consumerCreateDTO.toEntity();
		when(consumerRepository.save(any())).thenReturn(consumer);
		
		service.create(consumerCreateDTO);
		

		assertEquals(consumer.getName(), consumerCreateDTO.getName());
		assertEquals(consumer.getDocumentNumber(), consumerCreateDTO.getDocumentNumber());
		assertEquals(consumer.getBirthDate(), consumerCreateDTO.getBirthDate());
		assertEquals(consumer.getAddress().size(), 2);
		assertEquals(consumer.getCards().size(), 3);
		assertEquals(consumer.getContacts().size(), 3);
		verify(consumerRepository, times(1)).save(any());
	}
	
	@Test
	public void whenUpdate_thenReturnSuccess() {
		ConsumerCreateDTO consumerCreateDTO = ConsumerCreateDTOMock.create();
		Consumer consumer = consumerCreateDTO.toEntity();
		when(consumerRepository.save(any())).thenReturn(consumer);
		
		service.create(consumerCreateDTO);
		

		assertEquals(consumer.getName(), consumerCreateDTO.getName());
		assertEquals(consumer.getDocumentNumber(), consumerCreateDTO.getDocumentNumber());
		assertEquals(consumer.getBirthDate(), consumerCreateDTO.getBirthDate());
		assertEquals(consumer.getAddress().size(), 2);
		assertEquals(consumer.getCards().size(), 3);
		assertEquals(consumer.getContacts().size(), 3);
		verify(consumerRepository, times(1)).save(any());
	}
}
