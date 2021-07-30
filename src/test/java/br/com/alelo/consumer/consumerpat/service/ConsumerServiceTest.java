package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.BaseMockitoTest;
import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static br.com.alelo.consumer.consumerpat.builder.ConsumerBuilder.consumerWith2Cards;
import static br.com.alelo.consumer.consumerpat.builder.ConsumerBuilder.emptyConsumer;
import static br.com.alelo.consumer.consumerpat.builder.CreateConsumerBuilder.fullCreateConsumerDTO;
import static br.com.alelo.consumer.consumerpat.builder.UpdateConsumerBuilder.fullUpdateConsumerDTO;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsumerServiceTest extends BaseMockitoTest {
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

        assertCreateConsumer(request, capturedConsumer);
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
        final Consumer consumer = consumerWith2Cards();
        consumer.getCards().forEach(it -> it.setConsumer(consumer));
        doReturn(Optional.of(consumer)).when(consumerRepository).findById(request.getId());

        target.update(request);

        verify(consumerRepository).save(consumerCaptor.capture());
        Consumer capturedConsumer = consumerCaptor.getValue();

        assertUpdateConsumer(request, capturedConsumer);
    }

    public static void assertUpdateConsumer(UpdateConsumerDTO request, Consumer consumer) {
        assertEquals(request.getName(), consumer.getName());
        assertEquals(request.getDocumentNumber(), consumer.getDocumentNumber());
        assertEquals(request.getBirthDate(), consumer.getBirthDate());
        assertEquals(request.getMobilePhoneNumber(), consumer.getMobilePhoneNumber());
        assertEquals(request.getResidencePhoneNumber(), consumer.getResidencePhoneNumber());
        assertEquals(request.getPhoneNumber(), consumer.getPhoneNumber());
        assertEquals(request.getEmail(), consumer.getEmail());
        assertEquals(request.getStreet(), consumer.getStreet());
        assertEquals(request.getNumber(), consumer.getNumber());
        assertEquals(request.getCity(), consumer.getCity());
        assertEquals(request.getCountry(), consumer.getCountry());
        assertEquals(request.getPostalCode(), consumer.getPostalCode());

        assertUpdateCards(request.getCards(), consumer);
    }

    public static void assertCreateConsumer(CreateConsumerDTO request, Consumer consumer) {
        assertEquals(request.getName(), consumer.getName());
        assertEquals(request.getDocumentNumber(), consumer.getDocumentNumber());
        assertEquals(request.getBirthDate(), consumer.getBirthDate());
        assertEquals(request.getMobilePhoneNumber(), consumer.getMobilePhoneNumber());
        assertEquals(request.getResidencePhoneNumber(), consumer.getResidencePhoneNumber());
        assertEquals(request.getPhoneNumber(), consumer.getPhoneNumber());
        assertEquals(request.getEmail(), consumer.getEmail());
        assertEquals(request.getStreet(), consumer.getStreet());
        assertEquals(request.getNumber(), consumer.getNumber());
        assertEquals(request.getCity(), consumer.getCity());
        assertEquals(request.getCountry(), consumer.getCountry());
        assertEquals(request.getPostalCode(), consumer.getPostalCode());

        assertCreateCards(request.getCards(), consumer);
    }

    private static void assertCreateCards(List<CreateCardDTO> cardRequestList, Consumer consumer) {
        Map<String, CreateCardDTO> cardsMap = cardRequestList
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(CreateCardDTO::getNumber, Function.identity()));

        assertEquals(2, consumer.getCards().size());
        consumer.getCards().forEach(it -> {
            assertNonNullCardFields(consumer, it);

            CreateCardDTO cardRequest = cardsMap.get(it.getNumber());
            assertNotNull(cardRequest);
            assertEquals(cardRequest.getNumber(), it.getNumber());
            assertArrayEquals(cardRequest.getTypes().toArray(), it.getTypes().toArray());
            if (nonNull(cardRequest.getBalance())) {
                assertEquals(cardRequest.getBalance(), it.getBalance());
            }
        });
    }

    private static void assertUpdateCards(List<UpdateCardDTO> cardRequestList, Consumer consumer) {
        Map<String, UpdateCardDTO> cardsMap = cardRequestList
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(UpdateCardDTO::getNumber, Function.identity()));

        //
        assertEquals(3, consumer.getCards().size());
        consumer.getCards().forEach(it -> {
            assertNonNullCardFields(consumer, it);

            UpdateCardDTO cardRequest = cardsMap.get(it.getNumber());
            if (nonNull(cardRequest)) {// Insert or Update of a card
                assertEquals(cardRequest.getNumber(), it.getNumber());
                assertArrayEquals(cardRequest.getTypes().toArray(), it.getTypes().toArray());
                if (nonNull(cardRequest.getId())) {
                    assertEquals(cardRequest.getId(), it.getId());
                }
            }
        });
    }

    private static void assertNonNullCardFields(Consumer consumer, br.com.alelo.consumer.consumerpat.entity.Card it) {
        assertNotNull(it);
        assertNotNull(it.getBalance());
        assertNotNull(it.getNumber());
        assertNotNull(it.getConsumer());
        assertEquals(consumer, it.getConsumer());
    }
}