package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CardServiceTest {


    private final Integer ID_CONSUMER = 1;
    private final Integer CARD_ID = 2;
    private final Integer CARD_NUMBER = 12345678;
    private final Integer CARD_NUMBER_NEW = 123456789;
    private final Integer DOCUMENT_NUMBER = 123456789;
    private final BigDecimal BALANCE_CARD = BigDecimal.valueOf(3000.00);


    @Mock
    private CardService service;
    @Mock
    private CardRepository repository;

    @Mock
    private Card cardMock;
    @Mock
    private CardDTO cardDTOMock;

    @Mock
    private Consumer consumer;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(this.consumer.getId()).thenReturn(ID_CONSUMER);
        when(this.consumer.getDocumentNumber()).thenReturn(DOCUMENT_NUMBER);

        when(this.cardMock.getCardType()).thenReturn(CardType.FOOD);
        when(this.cardMock.getCardNumber()).thenReturn(CARD_NUMBER);
        when(this.cardMock.getBalanceValue()).thenReturn(BALANCE_CARD);
        when(this.cardMock.getConsumer()).thenReturn(consumer);

        when(this.cardDTOMock.getCardType()).thenReturn(CardType.FOOD);
        when(this.cardDTOMock.getCardNumber()).thenReturn(CARD_NUMBER);
        when(this.cardDTOMock.getBalanceValue()).thenReturn(BALANCE_CARD);
        when(this.cardDTOMock.getConsumer()).thenReturn(consumer);

        when(repository.save(cardMock)).thenReturn(cardMock);
    }

    @Test
    void should_save() throws BusinessException {

        CardCreateResponseDTO card =  CardCreateResponseDTO.builder()
                .cardType(cardDTOMock.getCardType())
                .cardNumber(cardDTOMock.getCardNumber())
                .build();
        when(service.save(cardDTOMock)).thenReturn(card);

        assertEquals(CARD_NUMBER, card.getCardNumber());
    }

    @Test
    void should_findByCardNumber() {
        when(service.findByCardNumber(CARD_NUMBER)).thenReturn(cardDTOMock);
        var cardFind = service.findByCardNumber(CARD_NUMBER);
        assertEquals(CARD_NUMBER, cardFind.getCardNumber());
    }

    @Test
    void should_findByCardNumber_not_found() {
        when(service.findByCardNumber(-1)).thenThrow(new EntityNotFoundException("Não foi encontrado um Cartão para o número informado"));
        EntityNotFoundException exception =  assertThrows(EntityNotFoundException.class, () -> service.findByCardNumber(-1));
        assertEquals("Não foi encontrado um Cartão para o número informado", exception.getMessage());
    }


    @Test
    void should_debtByCardw_hith_balance_enough() throws BusinessException {
        BigDecimal debit = BigDecimal.valueOf(2000.00);
        Card cardDebit = Mockito.mock(Card.class);
        when(cardDebit.getBalanceValue()).thenReturn(BigDecimal.valueOf(1000.00));
        when(service.debtByCard(cardMock, debit)).thenReturn(cardDebit);
        Card card = service.debtByCard(cardMock, debit);
        assertEquals(0, card.getBalanceValue().compareTo(BigDecimal.valueOf(1000.00)));
    }

    @Test
    void should_debtByCard_whith_balance_not_enough() throws BusinessException {
        BigDecimal debit = BigDecimal.valueOf(4000.00);
        when(service.debtByCard(cardMock, debit )).thenThrow(new BusinessException("Não existe Crédito suficiente para transação"));
        BusinessException exception =  assertThrows(BusinessException.class, () -> service.debtByCard(cardMock, debit));
        assertEquals("Não existe Crédito suficiente para transação", exception.getMessage());
    }

    @Test
    void should_credit() {
        BigDecimal credit = BigDecimal.valueOf(2000.00);

        CardDTO cardIncrease = Mockito.mock(CardDTO.class);
        when( cardIncrease.getBalanceValue()).thenReturn(BigDecimal.valueOf(5000.00));

        when(service.credit(CARD_NUMBER, credit)).thenReturn(cardIncrease);
        CardDTO dtoTransaction = service.credit(CARD_NUMBER, credit);
        assertEquals(0, cardIncrease.getBalanceValue().compareTo(BigDecimal.valueOf(5000.00)));
    }

    @Test
    void should_creditByCard() {
        BigDecimal debit = BigDecimal.valueOf(3000.00);
        Card cardIncrease = Mockito.mock(Card.class);
        when(cardIncrease.getBalanceValue()).thenReturn(BigDecimal.valueOf(6000.00));
        when(service.creditByCard(cardMock, debit)).thenReturn(cardIncrease);
        Card card = service.creditByCard(cardMock, debit);
        assertEquals(0, card.getBalanceValue().compareTo(BigDecimal.valueOf(6000.00)));
    }
}