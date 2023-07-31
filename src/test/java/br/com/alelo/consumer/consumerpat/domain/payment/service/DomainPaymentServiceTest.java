package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.repository.DomainPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DomainPaymentServiceTest {

    private DomainPaymentService domainPaymentService;
    private DomainPaymentRepository paymentRepository;
    private CardService cardService;
    private LedgerService ledgerService;

    @BeforeEach
    void setUp() {
        // Create mocks for the dependencies
        paymentRepository = mock(DomainPaymentRepository.class);
        cardService = mock(CardService.class);
        ledgerService = mock(LedgerService.class);

        domainPaymentService = new DomainPaymentService(paymentRepository, cardService, ledgerService);
    }

    @Test
    void testRegisterPaymentSuccess() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        var cardBalance = new CardBalance(cardNumber);
        cardBalance.chargeCardBalance(BigDecimal.valueOf(100));
        var card = new Card(cardNumber, cardType);
        card.addCardBalance(cardBalance);

        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);
        var newPayment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);
        newPayment.addId(UUID.randomUUID());

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.of(card));

        when(cardService.searchCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance));

        domainPaymentService.registerPayment(newPayment);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(cardService, times(1)).updateCard(any(Card.class));
        verify(ledgerService, times(1)).debit(any(Payment.class));
    }

    @Test
    void testRegisterPaymentCardNotFound() {
        CardNumber cardNumber = new CardNumber("1234567812345678");

        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);
        var newPayment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);
        newPayment.addId(UUID.randomUUID());

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.empty());

        when(cardService.searchCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> domainPaymentService.registerPayment(newPayment));

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(cardService, never()).updateCard(any(Card.class));
        verify(ledgerService, never()).debit(any(Payment.class));
    }

    @Test
    void testRegisterPaymentNotAllowed() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FUEL;
        var cardBalance = new CardBalance(cardNumber);
        cardBalance.chargeCardBalance(BigDecimal.valueOf(100));
        var card = new Card(cardNumber, cardType);
        card.addCardBalance(cardBalance);

        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);
        var newPayment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);
        newPayment.addId(UUID.randomUUID());

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.of(card));

        when(cardService.searchCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance));

        assertThrows(DomainException.class, () -> domainPaymentService.registerPayment(newPayment));

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(cardService, never()).updateCard(any(Card.class));
        verify(ledgerService, never()).debit(any(Payment.class));
    }
}
