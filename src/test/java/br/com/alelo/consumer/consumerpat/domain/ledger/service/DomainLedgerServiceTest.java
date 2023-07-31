package br.com.alelo.consumer.consumerpat.domain.ledger.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.ledger.entity.LedgerRecord;
import br.com.alelo.consumer.consumerpat.domain.ledger.repository.DomainLedgerRepository;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class DomainLedgerServiceTest {

    private DomainLedgerService domainLedgerService;
    private DomainLedgerRepository ledgerRepository;

    @BeforeEach
    void setUp() {
        ledgerRepository = mock(DomainLedgerRepository.class);

        domainLedgerService = new DomainLedgerService(ledgerRepository);
    }

    @Test
    void testCredit() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = BigDecimal.valueOf(100);
        var cardBalance = new CardBalance(cardNumber);
        cardBalance.chargeCardBalance(amount);
        var card = new Card(cardNumber, CardType.FOOD);
        card.addCardBalance(cardBalance);

        domainLedgerService.credit(card);

        verify(ledgerRepository, times(1)).save(any(LedgerRecord.class));
    }

    @Test
    void testDebit() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = BigDecimal.valueOf(50);
        Payment payment = new Payment(
                new Establishment("Restaurant", EstablishmentType.FOOD), "Food", LocalDate.now(), cardNumber, amount);

        domainLedgerService.debit(payment);

        verify(ledgerRepository, times(1)).save(any(LedgerRecord.class));
    }
}
