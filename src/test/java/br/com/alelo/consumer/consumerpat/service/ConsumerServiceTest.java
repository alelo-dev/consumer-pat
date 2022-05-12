package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.validator.ConsumerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.*;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_NOT_FOUND;
import static java.util.Optional.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ConsumerServiceTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @Mock
    private MessageService messageService;

    @Mock
    private ConsumerValidator consumerValidator;

    @Mock
    private CardService cardService;

    @InjectMocks
    private ConsumerService consumerService;

    private Consumer consumer;

    @BeforeEach
    public void setUp() {

        final Card card = Card.builder().id(1L).number("123").type(FOOD).balance(100.0).build();

        //não é usado List.of() porque para alguns testes a lista deve ser mutável
        final List<Card> cards = new ArrayList<>(Arrays.asList(card));
        consumer = Consumer.builder().id(1L).name("teste").documentNumber("123456").cards(cards).build();
    }

    @Test
    public void testCreateConsumer() {

        consumerService.createConsumer(consumer);

        verify(consumerValidator, times(1)).accept(any(Consumer.class));
        verify(consumerRepository, times(1)).save(any(Consumer.class));
    }

    @Test
    public void testUpdateConsumerOk() {

        doNothing().when(cardService).adjustCard(any(Card.class));
        when(consumerRepository.findConsumerByDocumentNumber(eq(consumer.getDocumentNumber()))).thenReturn(of(consumer));

        consumerService.updateConsumer(consumer);

        verify(consumerValidator, times(1)).accept(any(Consumer.class));
        verify(consumerRepository, times(1)).save(any(Consumer.class));
    }

    @Test
    public void testUpdateConsumerNotFound() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () ->
                consumerService.updateConsumer(consumer)
        );

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CONSUMER_NOT_FOUND.getCode()));
    }

    @Test
    public void testAdjustConsumer() {

        final String maskedDocument = "12.345-6";
        consumer.setDocumentNumber(maskedDocument);
        doNothing().when(cardService).adjustCard(any(Card.class));

        assertEquals(maskedDocument, consumer.getDocumentNumber());
        consumerService.adjustConsumer(consumer);

        assertEquals("123456", consumer.getDocumentNumber());
        verify(consumerValidator, times(1)).accept(any(Consumer.class));
    }

    @Test
    public void testUpdateConsumerCardsAllNew() {

        final Card newCard1 = Card.builder().id(2L).number("456").type(FUEL).balance(100.0).build();
        final Card newCard2 = Card.builder().id(3L).number("789").type(DRUGSTORE).balance(100.0).build();

        final List<Card> newCards = new ArrayList<>(Arrays.asList(newCard1, newCard2));

        assertEquals(2, newCards.size());
        consumerService.updateConsumerCards(newCards, consumer.getCards());

        assertEquals(3, newCards.size());
    }

    @Test
    public void testUpdateConsumerCardsOneNewOneWrong() {

        final Card newCard1 = Card.builder().id(2L).number("123").type(FUEL).balance(100.0).build();
        final Card newCard2 = Card.builder().id(3L).number("456").type(DRUGSTORE).balance(100.0).build();

        final List<Card> newCards = new ArrayList<>(Arrays.asList(newCard1, newCard2));

        assertEquals(2, newCards.size());
        assertEquals(FUEL, newCards.get(0).getType());
        assertEquals("123", newCards.get(0).getNumber());
        consumerService.updateConsumerCards(newCards, consumer.getCards());

        assertEquals(2, newCards.size());
        final Card cardForAssertion =
                newCards.stream().filter(card -> "123".equals(card.getNumber())).findFirst().get();
        assertEquals(FOOD, cardForAssertion.getType());
        assertEquals("123", cardForAssertion.getNumber());
    }

    @Test
    public void testUpdateConsumerCardsDiscontinue() {

        final Card newCard1 = Card.builder().id(2L).number("123").type(FOOD).discontinued(true).build();

        final List<Card> newCards = new ArrayList<>(Arrays.asList(newCard1));

        assertEquals(1, newCards.size());
        assertEquals("123", consumer.getCards().get(0).getNumber());
        assertFalse(consumer.getCards().get(0).isDiscontinued());
        consumerService.updateConsumerCards(newCards, consumer.getCards());

        assertEquals(1, newCards.size());
        assertEquals("123", newCards.get(0).getNumber());
        assertTrue(newCards.get(0).isDiscontinued());
    }

    @Test
    public void testFindAll() {

        when(consumerRepository.findAll()).thenReturn(List.of(consumer));
        final List<Consumer> consumers = consumerService.findAll();

        assertEquals(1, consumers.size());
    }

    @Test
    public void testFindById() {

        final Long id = 1L;
        when(consumerRepository.findById(eq(id))).thenReturn(ofNullable(consumer));
        final Consumer response = consumerService.findById(id);

        assertEquals("123456", response.getDocumentNumber());
    }

    @Test
    public void testFindConsumerByIdNotFound() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Long id = 1L;
            when(consumerRepository.findById(eq(id))).thenReturn(empty());
            consumerService.findById(id);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CONSUMER_NOT_FOUND.getCode()));
    }

    @Test
    void testFindConsumerByDocumentNumber() {

        final String documentNumber = "123456";
        when(consumerRepository.findConsumerByDocumentNumber(eq(documentNumber))).thenReturn(ofNullable(consumer));
        final Consumer consumer = consumerService.findConsumerByDocumentNumber(documentNumber);

        assertEquals(documentNumber, consumer.getDocumentNumber());
    }

    @Test
    void testFindConsumerByDocumentNumberNotFound() {
        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final String documentNumber = "123456";
            when(consumerRepository.findConsumerByDocumentNumber(eq(documentNumber))).thenReturn(empty());
            consumerService.findConsumerByDocumentNumber(documentNumber);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CONSUMER_NOT_FOUND.getCode()));
    }
}