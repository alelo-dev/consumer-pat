package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.repository.PaymentRepository;
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
    private PaymentRepository paymentRepository;
    private CardService cardService;
    private LedgerService ledgerService;

    @BeforeEach
    void setUp() {
        // Create mocks for the dependencies
        paymentRepository = mock(PaymentRepository.class);
        cardService = mock(CardService.class);
        ledgerService = mock(LedgerService.class);

        domainPaymentService = new DomainPaymentService(paymentRepository, cardService, ledgerService);
    }

    @Test
    void testRegisterPaymentSuccess() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        UUID consumerId = UUID.randomUUID();
        CardBalance cardBalance = new CardBalance(UUID.randomUUID(), new Card(cardNumber, cardType, consumerId));
        cardBalance.chargeCardBalance(BigDecimal.valueOf(100));

        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance.getCard()));

        when(cardService.searchCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance));

        domainPaymentService.registerPayment(establishment, productDescription, buyDate, cardNumber, amount);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(cardService, times(1)).updateCardBalance(any(CardBalance.class));
        verify(ledgerService, times(1)).credit(any(CardBalance.class));
    }

    @Test
    void testRegisterPaymentCardNotFound() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(DomainException.class, () -> domainPaymentService.registerPayment(establishment, productDescription, buyDate, cardNumber, amount));

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(cardService, never()).updateCardBalance(any(CardBalance.class));
        verify(ledgerService, never()).credit(any(CardBalance.class));
    }

    @Test
    void testRegisterPaymentCardBalanceNotFound() {
        // Create test data
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        UUID consumerId = UUID.randomUUID();
        CardBalance cardBalance = new CardBalance(UUID.randomUUID(), new Card(cardNumber, cardType, consumerId));

        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance.getCard()));

        when(cardService.searchCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(DomainException.class, () -> domainPaymentService.registerPayment(establishment, productDescription, buyDate, cardNumber, amount));

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(cardService, never()).updateCardBalance(any(CardBalance.class));
        verify(ledgerService, never()).credit(any(CardBalance.class));
    }

    @Test
    void testRegisterPaymentNotAllowed() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FUEL; // Not allowed at the Restaurant establishment
        UUID consumerId = UUID.randomUUID();
        CardBalance cardBalance = new CardBalance(UUID.randomUUID(), new Card(cardNumber, cardType, consumerId));

        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Food";
        LocalDate buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(50);

        when(cardService.searchCardByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance.getCard()));

        when(cardService.searchCardBalanceByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalance));

        assertThrows(DomainException.class, () -> domainPaymentService.registerPayment(establishment, productDescription, buyDate, cardNumber, amount));

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(cardService, never()).updateCardBalance(any(CardBalance.class));
        verify(ledgerService, never()).credit(any(CardBalance.class));
    }
}
