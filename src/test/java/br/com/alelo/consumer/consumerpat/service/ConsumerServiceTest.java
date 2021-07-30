package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.MockitoBaseTest;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.builder.ConsumerBuilder.consumerWith2Cards;
import static br.com.alelo.consumer.consumerpat.builder.ConsumerBuilder.emptyConsumer;
import static br.com.alelo.consumer.consumerpat.builder.CreateConsumerBuilder.fullCreateConsumerDTO;
import static br.com.alelo.consumer.consumerpat.builder.UpdateConsumerBuilder.fullUpdateConsumerDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsumerServiceTest extends MockitoBaseTest {
    @Mock
    private ConsumerRepository consumerRepository;
    @Spy
    private ConsumerMapper consumerMapper;
    @InjectMocks
    private ConsumerService target;

    @Captor
    private ArgumentCaptor<Consumer> consumerCaptor;

    @Test
    public void shouldFindAll() {
        final List<Consumer> dbList = Collections.singletonList(emptyConsumer());
        when(consumerRepository.findAllWithCards()).thenReturn(dbList);

        List<ConsumerDTO> result = target.getAllConsumersList();
        assertEquals(dbList.size(), result.size());
    }

    @Test
    public void shouldCreate() {
        doReturn(emptyConsumer()).when(consumerRepository).save(any(Consumer.class));
        final CreateConsumerDTO request = fullCreateConsumerDTO();

        target.create(request);

        verify(consumerRepository).save(consumerCaptor.capture());
        Consumer capturedConsumer = consumerCaptor.getValue();

        assertEquals(request.getName(), capturedConsumer.getName());
        assertEquals(request.getDocumentNumber(), capturedConsumer.getDocumentNumber());
        assertEquals(request.getBirthDate(), capturedConsumer.getBirthDate());
        assertEquals(request.getMobilePhoneNumber(), capturedConsumer.getMobilePhoneNumber());
        assertEquals(request.getResidencePhoneNumber(), capturedConsumer.getResidencePhoneNumber());
        assertEquals(request.getPhoneNumber(), capturedConsumer.getPhoneNumber());
        assertEquals(request.getEmail(), capturedConsumer.getEmail());
        assertEquals(request.getStreet(), capturedConsumer.getStreet());
        assertEquals(request.getNumber(), capturedConsumer.getNumber());
        assertEquals(request.getCity(), capturedConsumer.getCity());
        assertEquals(request.getCountry(), capturedConsumer.getCountry());
        assertEquals(request.getPostalCode(), capturedConsumer.getPostalCode());

        assertEquals(2, capturedConsumer.getCards().size());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenNoCustomerId() {
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> target.findById(null),
                "Expected findById() to throw, but it didn't"
        );
        assertTrue(thrown.getDetails().contains(Constants.CONSUMER_NOT_FOUND));
    }

    @Test
    public void shouldUpdate() {
        doReturn(emptyConsumer()).when(consumerRepository).save(any(Consumer.class));

        final UpdateConsumerDTO request = fullUpdateConsumerDTO();
        doReturn(Optional.of(consumerWith2Cards())).when(consumerRepository).findById(request.getId());

        target.update(request);

        verify(consumerRepository).save(consumerCaptor.capture());
        Consumer capturedConsumer = consumerCaptor.getValue();

        assertEquals(request.getName(), capturedConsumer.getName());
        assertEquals(request.getDocumentNumber(), capturedConsumer.getDocumentNumber());
        assertEquals(request.getBirthDate(), capturedConsumer.getBirthDate());
        assertEquals(request.getMobilePhoneNumber(), capturedConsumer.getMobilePhoneNumber());
        assertEquals(request.getResidencePhoneNumber(), capturedConsumer.getResidencePhoneNumber());
        assertEquals(request.getPhoneNumber(), capturedConsumer.getPhoneNumber());
        assertEquals(request.getEmail(), capturedConsumer.getEmail());
        assertEquals(request.getStreet(), capturedConsumer.getStreet());
        assertEquals(request.getNumber(), capturedConsumer.getNumber());
        assertEquals(request.getCity(), capturedConsumer.getCity());
        assertEquals(request.getCountry(), capturedConsumer.getCountry());
        assertEquals(request.getPostalCode(), capturedConsumer.getPostalCode());

        assertEquals(3, capturedConsumer.getCards().size());
    }
}