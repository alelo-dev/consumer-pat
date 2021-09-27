package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.service.ExtractStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ExtractFactory {

    private Map<EstablishmentType, ExtractStrategy> strategies;

    @Autowired
    public ExtractFactory(Set<ExtractStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public ExtractStrategy findStrategyByEstablishmentId(EstablishmentType strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<ExtractStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getStrategyName(), strategy));
    }

}
