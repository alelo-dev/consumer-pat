package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static java.lang.String.format;

public class DomainPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CardService cardService;
    private final LedgerService ledgerService;

    public DomainPaymentService(PaymentRepository paymentRepository,
                                CardService cardService,
                                LedgerService ledgerService) {
        this.paymentRepository = paymentRepository;
        this.cardService = cardService;
        this.ledgerService = ledgerService;
    }

    public void registerPayment(Establishment establishment,
                                String productDescription,
                                LocalDate buyDate,
                                CardNumber cardNumber,
                                BigDecimal amount) {

        var car = cardService.searchCardByCardNumber(cardNumber)
                .orElseThrow(() -> new DomainException(format("Card [%s] not found", cardNumber)));

        var newPayment = new Payment(UUID.randomUUID(), establishment, productDescription, buyDate, cardNumber, amount);
        newPayment.addPaymentStrategy(car.getCardType());

        if (!newPayment.getPaymentStrategy().isEstablishmentAllowed(establishment.getEstablishmentType())) {
            throw new DomainException("Payment type not permitted");
        }

        var cardBalance = cardService.searchCardBalanceByCardNumber(cardNumber)
                .orElseThrow(() ->
                        new DomainException(format("Card balance with card number [%s] not found", cardNumber)));

        cardBalance.withdrawCardBalance(amount);

        paymentRepository.save(newPayment);
        cardService.updateCardBalance(cardBalance);
        ledgerService.credit(cardBalance);
    }
}
