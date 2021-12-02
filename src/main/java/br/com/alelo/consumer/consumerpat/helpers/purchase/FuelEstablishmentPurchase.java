package br.com.alelo.consumer.consumerpat.helpers.purchase;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.helpers.purchase.enums.PurchaseNamesEnum;
import br.com.alelo.consumer.consumerpat.helpers.purchase.strategies.PurchaseStrategy;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class FuelEstablishmentPurchase implements PurchaseStrategy {

    private final ConsumerRepository repository;

    @Override
    public double buy(int cardNumber, double value) {

        double tax  = (value / 100) * 35;
        value = value + tax;

        Consumer consumer = repository.findByCardFuelCardNumber(cardNumber)
                .orElseThrow(() -> {
                    log.warn("Consumer not found with Card {}", cardNumber);
                    return new IllegalArgumentException(""); //TODO Change Exception to not found
                });

        final double newBalance = consumer.getCard().getFuelCardBalance() - value;
        consumer.getCard()
                .setFuelCardBalance(newBalance);

        repository.save(consumer);
        return value;
    }

    @Override
    public PurchaseNamesEnum getPurchaseName() {
        return PurchaseNamesEnum.FuelEstablishmentPurchase;
    }
}
