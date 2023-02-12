package br.com.alelo.consumer.consumerpat.service.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CardOperationFactory {

    private Map<CardTypeEnum, CardOperation> strategies;

    @Autowired
    public CardOperationFactory(Set<CardOperation> strategySet) {
        createStrategy(strategySet);
    }

    public CardOperation findType(CardTypeEnum strategyName) {
        return strategies.get(strategyName);
    }

    public CardOperation findType(Consumer consumer, long cardNumber) {

        CardTypeEnum cardTypeEnum = null;
        if (consumer.getFoodCardNumber().compareTo(cardNumber) == 0) {
            cardTypeEnum = CardTypeEnum.Food;
        }else if (consumer.getDrugstoreNumber().compareTo(cardNumber) == 0) {
            cardTypeEnum = CardTypeEnum.DrugStore;
        }else if (consumer.getFuelCardNumber().compareTo(cardNumber) == 0) {
            cardTypeEnum = CardTypeEnum.Fuel;
        }

        return strategies.get(cardTypeEnum);
    }

    private void createStrategy(Set<CardOperation> strategySet) {
        strategies = new HashMap<CardTypeEnum, CardOperation>();
        strategySet.forEach(strategy -> strategies.put(strategy.getCardType(), strategy));
    }

}