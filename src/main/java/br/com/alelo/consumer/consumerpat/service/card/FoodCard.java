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
public class FoodCard implements CardOperation {

    private final ConsumerRepository consumerRepository;

    @Autowired
    public FoodCard(ConsumerRepository consumerRepository){
        this.consumerRepository = consumerRepository;
    }

    @Override
    public BigDecimal calculateDebitValue(BigDecimal value) {

        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        BigDecimal cashback = value.divide(new BigDecimal(100));
        cashback = cashback.multiply(new BigDecimal(10)).setScale(2, RoundingMode.DOWN);
        value = value.subtract(cashback);

        return value;
    }

    @Override
    @Transactional()
    public Consumer debit(long cardNumber, BigDecimal value) throws NotFoundException, InsufficientBalanceException {

        Consumer consumer = consumerRepository.findByFoodCardNumber(cardNumber)
                .orElseThrow(() -> new NotFoundException("Consumer not found"));

        BigDecimal balance = consumer.getFoodCardBalance().subtract(value);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        consumer.setFoodCardBalance(balance);
        consumerRepository.save(consumer);

        return consumer;

    }

    @Override
    @Transactional()
    public Consumer credit(Consumer consumer, BigDecimal value) throws NotFoundException {
        consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value));
        consumerRepository.save(consumer);
        return consumer;
    }

    @Override
    public CardTypeEnum getCardType() {
        return CardTypeEnum.Food;
    }

}