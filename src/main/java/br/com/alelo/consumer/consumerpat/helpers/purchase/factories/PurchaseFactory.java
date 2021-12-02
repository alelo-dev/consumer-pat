package br.com.alelo.consumer.consumerpat.helpers.purchase.factories;

import br.com.alelo.consumer.consumerpat.helpers.purchase.enums.PurchaseNamesEnum;
import br.com.alelo.consumer.consumerpat.helpers.purchase.strategies.PurchaseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
public class PurchaseFactory {

    private Map<PurchaseNamesEnum, PurchaseStrategy> strategies;

    @Autowired
    public PurchaseFactory(Set<PurchaseStrategy> purchaseStrategySet) {
        createStrategy(purchaseStrategySet);
    }

    public PurchaseStrategy findStrategy(final int establishmentType) {
        PurchaseNamesEnum strategyName = PurchaseNamesEnum.getPurchaseEnum(establishmentType);
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<PurchaseStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy -> strategies.put(strategy.getPurchaseName(), strategy));
    }


}
