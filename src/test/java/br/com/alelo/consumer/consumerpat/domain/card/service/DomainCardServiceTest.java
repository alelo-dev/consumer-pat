package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.card.repository.DomainCardRepository;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomainCardServiceTest {

    @Mock
    private DomainCardRepository cardRepository;

    @Mock
    private ConsumerService consumerService;

    @Mock
    private LedgerService ledgerService;

    @InjectMocks
    private DomainCardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCard() {
        UUID consumerId = UUID.randomUUID();
        CardNumber cardNumber = new CardNumber("1234567890123456");
        Card newCard = new Card(cardNumber, CardType.FOOD);

        Consumer consumer = new Consumer("John Doe", "123456789", LocalDate.of(1990, 1, 1), null, null);
        when(consumerService.searchConsumerById(consumerId)).thenReturn(Optional.of(consumer));
        when(cardRepository.findCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.empty());

        cardService.addCard(consumerId, newCard);

        verify(cardRepository).saveCard(newCard);
    }

    @Test
    void testSearchCardByCardNumber() {
        CardNumber cardNumber = new CardNumber("1234567890123456");
        Card card = new Card(cardNumber, CardType.FOOD);

        when(cardRepository.findCardByCardNumber(cardNumber)).thenReturn(Optional.of(card));

        Optional<Card> result = cardService.searchCardByCardNumber(cardNumber);

        assertTrue(result.isPresent());
        assertEquals(card, result.get());

        verify(cardRepository).findCardByCardNumber(cardNumber);
    }

    @Test
    void testSearchCardByCardNumber_NotFound() {
        CardNumber cardNumber = new CardNumber("1234567890123456");

        when(cardRepository.findCardByCardNumber(cardNumber)).thenReturn(Optional.empty());

        Optional<Card> result = cardService.searchCardByCardNumber(cardNumber);

        assertFalse(result.isPresent());

        verify(cardRepository).findCardByCardNumber(cardNumber);
    }

    @Test
    void testAddCard_ConsumerNotFound() {
        UUID consumerId = UUID.randomUUID();
        CardNumber cardNumber = new CardNumber("1234567890123456");
        Card newCard = new Card(cardNumber, CardType.FOOD);

        when(consumerService.searchConsumerById(consumerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cardService.addCard(consumerId, newCard));

        verify(cardRepository, never()).saveCard(newCard);
    }

    @Test
    void testChargeCard() {
        CardNumber cardNumber = new CardNumber("1234567890123456");
        Card card = new Card(cardNumber, CardType.FOOD);
        card.addCardBalance(new CardBalance(cardNumber));

        BigDecimal initialBalance = new BigDecimal("100.00");
        BigDecimal chargeAmount = new BigDecimal("50.00");
        BigDecimal expectedBalance = initialBalance.subtract(chargeAmount);

        when(cardRepository.findCardByCardNumber(cardNumber)).thenReturn(Optional.of(card));

        cardService.chargeCard(cardNumber, chargeAmount);

        assertEquals(expectedBalance, card.getCardBalance().getAmount());

        verify(cardRepository).saveCard(card);

        verify(ledgerService).credit(card);
    }

    @Test
    void testChargeCard_CardNotFound() {
        CardNumber cardNumber = new CardNumber("1234567890123456");
        BigDecimal chargeAmount = new BigDecimal("50.00");

        when(cardRepository.findCardByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cardService.chargeCard(cardNumber, chargeAmount));

        verify(cardRepository, never()).saveCard(any(Card.class));

        verify(ledgerService, never()).credit(any(Card.class));
    }
}

