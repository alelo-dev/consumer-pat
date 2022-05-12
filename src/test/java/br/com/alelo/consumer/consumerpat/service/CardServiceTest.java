package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.model.request.AddBalanceRequest;
import br.com.alelo.consumer.consumerpat.model.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.types.CardAndEstablishmentType;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static utils.types.CardAndEstablishmentType.*;
import static utils.types.ExceptionsType.CARD_ILLEGAL_BALANCE_UPDATE;
import static utils.types.ExceptionsType.CARD_NOT_FROM_CONSUMER;

@ExtendWith(SpringExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ConsumerService consumerService;

    @Mock
    private EstablishmentService establishmentService;

    @Mock
    private ExtractService extractService;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private CardService cardService;

    private Card card;

    @BeforeEach
    public void setUp() {

        card = Card.builder().number("123").type(FOOD).balance(100.0).build();
    }

    @Test
    public void testSaveAll() {

        final List<Card> cards = List.of(card);

        cardService.saveAll(cards);
        verify(cardRepository, times(1)).saveAll(cards);
    }

    @Test
    public void testAddBalanceOk() {

        final String documentNumber = "123456";
        final String cardNumber = "1234";
        final AddBalanceRequest addBalanceRequest = new AddBalanceRequest(documentNumber, cardNumber, 10.0);
        final Card card =
                Card.builder().id(1L).number(cardNumber).type(FOOD).balance(100.0).discontinued(false).build();
        final Consumer consumer = Consumer.builder().id(1L).documentNumber(documentNumber).cards(List.of(card)).build();

        when(cardRepository.findByCardNumber(eq(addBalanceRequest.getCardNumber()))).thenReturn(ofNullable(card));
        when(consumerService.findConsumerByDocumentNumber(eq(documentNumber)))
                .thenReturn(consumer);

        cardService.addBalance(addBalanceRequest);

        final ArgumentCaptor<Card> argumentCard = ArgumentCaptor.forClass(Card.class);
        verify(cardRepository, times(1)).save(argumentCard.capture());

        assertEquals(110.0, argumentCard.getValue().getBalance());
    }

    @Test
    public void testAddBalanceCardNotFromConsumer() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final String documentNumber = "123456";
            final String cardNumber = "1234";
            final AddBalanceRequest addBalanceRequest = new AddBalanceRequest(documentNumber, cardNumber, 10.0);
            final Card card =
                    Card.builder().id(1L).number(cardNumber).type(FOOD).balance(100.0).discontinued(false).build();
            final Consumer consumer = Consumer.builder().id(1L).documentNumber(documentNumber).build();

            when(cardRepository.findByCardNumber(eq(addBalanceRequest.getCardNumber()))).thenReturn(ofNullable(card));
            when(consumerService.findConsumerByDocumentNumber(eq(addBalanceRequest.getConsumerDocumentNumber())))
                    .thenReturn(consumer);
            cardService.addBalance(addBalanceRequest);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CARD_NOT_FROM_CONSUMER.getCode()));
    }

    @Test
    public void testAddBalanceIllegalValue() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final String documentNumber = "123456";
            final String cardNumber = "1234";
            final AddBalanceRequest addBalanceRequest = new AddBalanceRequest(documentNumber, cardNumber, -10.0);

            cardService.addBalance(addBalanceRequest);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CARD_ILLEGAL_BALANCE_UPDATE.getCode()));
    }

    @ParameterizedTest
    @MethodSource("provideDataForBuySomethingOk")
    public void testBuySomethingOkDrugstore(CardAndEstablishmentType type, double finalBalance) {

        final String documentNumber = "123456";
        final String cardNumber = "1234";
        final Establishment establishment = Establishment.builder().id(1L).name("drugstore").type(type).build();
        final BuyRequest buyRequest = new BuyRequest(establishment, cardNumber, "123456", "product", new Date(), 10.0);
        final Card card =
                Card.builder().id(1L).number(cardNumber).type(type).balance(100.0).discontinued(false).build();
        final Consumer consumer = Consumer.builder().id(1L).documentNumber(documentNumber).cards(List.of(card)).build();

        when(establishmentService.getOrCreate(eq(establishment))).thenReturn(establishment);
        when(cardRepository.findByCardNumber(eq(cardNumber))).thenReturn(ofNullable(card));
        when(consumerService.findConsumerByDocumentNumber(eq(documentNumber)))
                .thenReturn(consumer);

        cardService.buySomething(buyRequest);

        final ArgumentCaptor<Card> argumentCard = ArgumentCaptor.forClass(Card.class);
        verify(cardRepository, times(1)).save(argumentCard.capture());
        assertEquals(finalBalance, argumentCard.getValue().getBalance());

        verify(extractService, times(1)).createExtract(any(BuyRequest.class), any(Establishment.class),
                any(Card.class));
    }

    private static Stream<Arguments> provideDataForBuySomethingOk() {
        return Stream.of(
                Arguments.of(DRUGSTORE, 90),
                Arguments.of(FOOD, 91),
                Arguments.of(FUEL, 86.5));
    }
}