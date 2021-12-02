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

import static br.com.alelo.consumer.consumerpat.helpers.validators.BalanceValidator.hasEnoughBalanceValidator;


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
                    return new PurchaseException(
                            ErrorCodeEnum.CONSUMER_NOT_FOUND,
                            ErrorMessages.NOT_FOUND, StringUtils.replace(
                            ValidationConstraints.CONSUMER_NOT_FOUND_BY_FOOD_CARD, "{}", String.valueOf(cardNumber)));
                });

        double currentBalance = consumer.getCard().getFoodCardBalance();
        hasEnoughBalanceValidator(currentBalance, value);

        final double newBalance = currentBalance - value;
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
