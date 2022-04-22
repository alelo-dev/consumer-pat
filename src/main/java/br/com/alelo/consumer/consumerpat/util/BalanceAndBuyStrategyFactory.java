package br.com.alelo.consumer.consumerpat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.service.BalanceAndBuyStrategy;

@Component
public class BalanceAndBuyStrategyFactory {
	
	private Map<EstablishmentType, BalanceAndBuyStrategy> strategies;

	@Autowired
	public BalanceAndBuyStrategyFactory(Set<BalanceAndBuyStrategy> strategySet) {
		createStrategy(strategySet);
	}

	public BalanceAndBuyStrategy findStrategy(EstablishmentType establishmentType) {
		return strategies.get(establishmentType);
	}

	private void createStrategy(Set<BalanceAndBuyStrategy> strategySet) {
		strategies = new HashMap<EstablishmentType, BalanceAndBuyStrategy>();
		strategySet.forEach(strategy -> strategies.put(strategy.getEstablishmentType(), strategy));
	}
}