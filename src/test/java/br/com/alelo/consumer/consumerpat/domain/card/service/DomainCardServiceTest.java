package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.card.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainCardServiceTest {

    private DomainCardService domainCardService;
    private CardRepository cardRepository;
    private ConsumerService consumerService;
    private LedgerService ledgerService;

    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        consumerService = mock(ConsumerService.class);
        ledgerService = mock(LedgerService.class);

        domainCardService = new DomainCardService(cardRepository, consumerService, ledgerService);
    }

    @Test
    void testAddCard() {
        // Create test data
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        UUID consumerId = UUID.randomUUID();

        when(consumerService.searchConsumerById(consumerId)).thenReturn(Optional.of(
                new Consumer(consumerId, "John Doe", "123456789", LocalDate.of(1990, 1, 1))));

        domainCardService.addCard(cardNumber, cardType, consumerId);

        verify(cardRepository, times(1)).saveCard(any(Card.class));
    }

    @Test
    void testAddCardConsumerNotFound() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        UUID nonExistentConsumerId = UUID.randomUUID();
        when(consumerService.searchConsumerById(nonExistentConsumerId)).thenReturn(Optional.empty());

        assertThrows(DomainException.class, () -> domainCardService.addCard(cardNumber, cardType, nonExistentConsumerId));

        verify(cardRepository, never()).saveCard(any(Card.class));
    }

    @Test
    void testChargeCard() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = BigDecimal.valueOf(100);

        CardBalance cardBalance = new CardBalance(UUID.randomUUID(), new Card(cardNumber, CardType.FOOD, UUID.randomUUID()));
        when(cardRepository.findCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance));

        domainCardService.chargeCard(cardNumber, amount);

        verify(cardRepository, times(1)).saveCardBalance(any(CardBalance.class));
        verify(ledgerService, times(1)).credit(any(CardBalance.class));
    }

    @Test
    void testChargeCardBalanceNotFound() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = BigDecimal.valueOf(100);

        when(cardRepository.findCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(DomainException.class, () -> domainCardService.chargeCard(cardNumber, amount));

        verify(cardRepository, never()).saveCardBalance(any(CardBalance.class));
        verify(ledgerService, never()).credit(any(CardBalance.class));
    }

    @Test
    void testUpdateCardBalance() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        UUID consumerId = UUID.randomUUID();

        CardBalance cardBalance = new CardBalance(UUID.randomUUID(), new Card(cardNumber, cardType, consumerId));

        domainCardService.updateCardBalance(cardBalance);

        verify(cardRepository, times(1)).saveCardBalance(cardBalance);
    }

    @Test
    void testSearchCardBalanceByCardNumberNotFound() {
        CardNumber nonExistentCardNumber = new CardNumber("9876543210987654");

        when(cardRepository.findCardBalanceByCardNumber(nonExistentCardNumber)).thenReturn(Optional.empty());

        Optional<CardBalance> result = domainCardService.searchCardBalanceByCardNumber(nonExistentCardNumber);
        assertFalse(result.isPresent(), "Card balance should not be found");
    }

    @Test
    void testSearchCardBalanceByCardNumberFound() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardBalance cardBalance = new CardBalance(UUID.randomUUID(), new Card(cardNumber, CardType.FOOD, UUID.randomUUID()));

        when(cardRepository.findCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance));

        Optional<CardBalance> result = domainCardService.searchCardBalanceByCardNumber(cardNumber);
        assertTrue(result.isPresent(), "Card balance should be found");
        assertEquals(cardBalance, result.get(), "Returned card balance should match");
    }

}
