package br.com.alelo.consumer.consumerpat.helpers.purchase;

import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import br.com.alelo.consumer.consumerpat.constants.ErrorMessages;
import br.com.alelo.consumer.consumerpat.constants.ValidationConstraints;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.PurchaseException;
import br.com.alelo.consumer.consumerpat.helpers.purchase.enums.PurchaseNamesEnum;
import br.com.alelo.consumer.consumerpat.helpers.purchase.strategies.PurchaseStrategy;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Slf4j
@Component
@RequiredArgsConstructor
public class DrugstoreEstablishmentPurchase implements PurchaseStrategy {

    private final ConsumerRepository repository;

    @Override
    public double buy(int cardNumber, double value) {
        Consumer consumer = repository.findByCardDrugstoreNumber(cardNumber)
                .orElseThrow(() -> {
                    log.warn("Consumer not found with Card {}", cardNumber);
                    return new PurchaseException(
                            ErrorCodeEnum.CONSUMER_NOT_FOUND,
                            ErrorMessages.NOT_FOUND, StringUtils.replace(
                            ValidationConstraints.CONSUMER_NOT_FOUND_BY_DRUGSTORE_CARD, "{}", String.valueOf(cardNumber)));
                });

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
