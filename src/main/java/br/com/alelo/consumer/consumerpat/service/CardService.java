package br.com.alelo.consumer.consumerpat.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Transaction;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.TransactionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository        cardRepository;
    private final TransactionRepository transactionRepository;
    
    @Transactional
    public Transaction bue(final Transaction transaction) {
        var card = cardRepository.findByNumberAndConsumer(transaction.getCard().getNumber(), transaction.getConsumer())
                .orElseThrow(() -> new NotFoundException("Card not found."));
        
        var value = card.caulculateValueWithTax(transaction.getValue());
        card.bue(value);
        transaction.setCard(card);
        transaction.setConfirmationCode(UUID.randomUUID().toString());
        transaction.setDateBuy(Instant.now());
        transaction.setValue(value);
        transaction.checkEstablishmentType();

        return transactionRepository.save(transaction);
    }
    
    @Transactional
    public void save(final Card card) {
        cardRepository.save(card);
    }

    @Transactional
    public void update(final Card card) {
        var c = cardRepository.findByIdAndConsumer(card.getId(), card.getConsumer()).map(card::mergeWithoutBalance)
                .orElseThrow(() -> new NotFoundException("Card not found."));
        cardRepository.save(c);
    }
    
}
