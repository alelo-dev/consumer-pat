package br.com.alelo.consumer.consumerpat.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.model.Card;
import br.com.alelo.consumer.consumerpat.domain.model.dto.PaymentInformation;
import br.com.alelo.consumer.consumerpat.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageBalanceTransactionsService {

    final CardRepository cardRepository;
    final RegisterExtractService registerExtract;

    public BigDecimal addCreditToCard(int cardNumber, BigDecimal value) {
        Card card = findOrFail(cardNumber);

        card.addCredit(value);
        cardRepository.save(card);
        return card.getBalance();
    }

    public BigDecimal makePayment(PaymentInformation paymentInformation) {
        Card card = findOrFail(paymentInformation.getCardNumber());
        
        BigDecimal paymentAmount = card.getType().calculatePaymentAmountBy(paymentInformation.getEstablishmentType(), paymentInformation.getValue());
        card.debitBalanceAmount(paymentAmount);
        
        registerExtract.save(paymentInformation);
        return card.getBalance();
    }

    private Card findOrFail(int cardNumber) {
        return cardRepository.findByNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException(String.format("Card number %d not found.", cardNumber)));
    }
}
