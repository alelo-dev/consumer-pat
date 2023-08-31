package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Set;

@Component
public class ExtractFactory {

    private EnumMap<CompanyType, ExtractStrategy> strategies;

    @Autowired
    public ExtractFactory(Set<ExtractStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public ExtractStrategy findStrategyByCompanyId(CompanyType strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<ExtractStrategy> strategySet) {
        strategies = new EnumMap<>(CompanyType.class);
        strategySet.forEach(strategy ->strategies.put(strategy.getStrategyName(), strategy));
    }

}
