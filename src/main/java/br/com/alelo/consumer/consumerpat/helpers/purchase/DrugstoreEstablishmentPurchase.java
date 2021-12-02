package br.com.alelo.consumer.consumerpat.helpers.purchase;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.helpers.purchase.enums.PurchaseNamesEnum;
import br.com.alelo.consumer.consumerpat.helpers.purchase.strategies.PurchaseStrategy;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DrugstoreEstablishmentPurchase implements PurchaseStrategy {

    private final ConsumerRepository repository;

    @Override
    public double buy(int cardNumber, double value) {
        Consumer consumer = repository.findByCardDrugstoreNumber(cardNumber);

        final double newBalance = consumer.getCard().getDrugstoreCardBalance() - value;
        consumer.getCard()
                .setDrugstoreCardBalance(newBalance);

        repository.save(consumer);

        return value;
    }

    @Override
    public PurchaseNamesEnum getPurchaseName() {
        return PurchaseNamesEnum.DrugstoreEstablishmentPurchase;
    }
}
