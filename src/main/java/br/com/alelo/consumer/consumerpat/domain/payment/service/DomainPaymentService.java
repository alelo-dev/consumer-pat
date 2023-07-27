package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.repository.PaymentRepository;

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

    public void registerPayment(final Payment newPayment) {
        newPayment.addId(UUID.randomUUID());
        var carFound = cardService.searchCardByCardNumber(newPayment.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("Card [%s] not found", newPayment.getCardNumber())));

        newPayment.addPaymentStrategy(carFound.getCardType());

        if (!newPayment.getPaymentStrategy().isEstablishmentAllowed(
                newPayment.getEstablishment().getEstablishmentType())) {
            throw new DomainException("Payment type not permitted");
        }

        var cardBalance = cardService.searchCardBalanceByCardNumber(newPayment.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("Card balance with card number [%s] not found", newPayment.getCardNumber())));

        cardBalance.withdrawCardBalance(newPayment.getAmount());

        paymentRepository.save(newPayment);
        cardService.updateCardBalance(cardBalance);
        ledgerService.credit(cardBalance);
    }
}
