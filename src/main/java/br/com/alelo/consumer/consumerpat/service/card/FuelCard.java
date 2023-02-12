package br.com.alelo.consumer.consumerpat.service.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.InsufficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FuelCard implements CardOperation {

    private final ConsumerRepository consumerRepository;

    @Autowired
    public FuelCard(ConsumerRepository consumerRepository){
        this.consumerRepository = consumerRepository;
    }

    @Override
    public BigDecimal calculateDebitValue(BigDecimal value) {

        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        BigDecimal tax = value.divide(new BigDecimal(100));
        tax = tax.multiply(new BigDecimal(35)).setScale(2, RoundingMode.DOWN);
        value = value.add(tax);

        return value;
    }

    @Override
    @Transactional()
    public Consumer debit(long cardNumber, BigDecimal value) throws NotFoundException, InsufficientBalanceException {

        Consumer consumer = consumerRepository.findByFuelCardNumber(cardNumber)
                .orElseThrow(() -> new NotFoundException("Consumer not found"));

        BigDecimal balance = consumer.getFuelCardBalance().subtract(value);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        consumer.setFuelCardBalance(balance);
        consumerRepository.save(consumer);

        return consumer;
    }

    @Override
    @Transactional()
    public Consumer credit(Consumer consumer, BigDecimal value) throws NotFoundException {
        consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value));
        consumerRepository.save(consumer);
        return consumer;
    }

    @Override
    public CardTypeEnum getCardType() {
        return CardTypeEnum.Fuel;
    }

}