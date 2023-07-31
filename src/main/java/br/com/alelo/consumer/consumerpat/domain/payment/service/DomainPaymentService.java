package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.repository.DomainPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DomainPaymentService implements PaymentService {

    private final DomainPaymentRepository paymentRepository;
    private final CardService cardService;
    private final LedgerService ledgerService;


    public void registerPayment(final Payment newPayment) {
        var carFound = cardService.searchCardByCardNumber(newPayment.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("Card number [%s] not found", newPayment.getCardNumber())));

        newPayment.addPaymentStrategy(carFound.getCardType());

        if (!newPayment.getPaymentStrategy().isEstablishmentAllowed(
                newPayment.getEstablishment().getEstablishmentType())) {
            throw new DomainException("Payment type not permitted");
        }

        carFound.getCardBalance().withdrawCardBalance(newPayment.getAmount());

        paymentRepository.save(newPayment);
        cardService.updateCard(carFound);
        ledgerService.debit(newPayment);
    }
}
