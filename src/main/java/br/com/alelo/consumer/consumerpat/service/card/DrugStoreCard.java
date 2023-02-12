package br.com.alelo.consumer.consumerpat.service.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.InsufficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DrugStoreCard implements CardOperation {

    private final ConsumerRepository consumerRepository;

    @Autowired
    public DrugStoreCard(ConsumerRepository consumerRepository){
        this.consumerRepository = consumerRepository;
    }

    @Override
    public BigDecimal calculateDebitValue(BigDecimal value) {
        return value;
    }

    @Override
    @Transactional()
    public Consumer debit(long cardNumber, BigDecimal value) throws NotFoundException, InsufficientBalanceException {

        Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber)
                .orElseThrow(() -> new NotFoundException("Consumer not found"));

        BigDecimal balance = consumer.getDrugstoreCardBalance().subtract(value);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        consumer.setDrugstoreCardBalance(balance);
        consumerRepository.save(consumer);

        return consumer;
    }

    @Override
    @Transactional()
    public Consumer credit(Consumer consumer, BigDecimal value) throws NotFoundException {
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(value));
        consumerRepository.save(consumer);
        return consumer;
    }

    @Override
    public CardTypeEnum getCardType() {
        return CardTypeEnum.DrugStore;
    }
}