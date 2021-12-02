package br.com.alelo.consumer.consumerpat.helpers.purchase;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.helpers.purchase.enums.PurchaseNamesEnum;
import br.com.alelo.consumer.consumerpat.helpers.purchase.strategies.PurchaseStrategy;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class FoodEstablishmentPurchase implements PurchaseStrategy {

    private final ConsumerRepository repository;

    @Override
    public double buy(int cardNumber, double value) {
        double cashback  = (value / 100) * 10;
        value = value - cashback;

        Consumer consumer = repository.findByCardFoodCardNumber(cardNumber)
                .orElseThrow(() -> {
                    log.warn("Consumer not found with Card {}", cardNumber);
                    return new IllegalArgumentException(""); //TODO Change Exception to not found
                });

        final double newBalance = consumer.getCard()
                .getFoodCardBalance() - value;

        consumer.getCard()
                .setFoodCardBalance(newBalance);

        repository.save(consumer);
        return value;
    }

    @Override
    public PurchaseNamesEnum getPurchaseName() {
        return PurchaseNamesEnum.FoodEstablishmentPurchase;
    }
}
