package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Set;

@Component
public class ExtractFactory {

    private EnumMap<EstablishmentType, ExtractStrategy> strategies;

    @Autowired
    public ExtractFactory(Set<ExtractStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public ExtractStrategy findStrategyByEstablishmentId(EstablishmentType strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<ExtractStrategy> strategySet) {
        strategies = new EnumMap<>(EstablishmentType.class);
        strategySet.forEach(strategy ->strategies.put(strategy.getStrategyName(), strategy));
    }

}
