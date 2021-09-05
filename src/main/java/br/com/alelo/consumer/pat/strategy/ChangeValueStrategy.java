package br.com.alelo.consumer.pat.strategy;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChangeValueStrategy {

    private Map<String, ValueChanger> strategies;

    public ChangeValueStrategy(final Map<String, ValueChanger> strategies) {
        this.strategies = strategies;
    }

    public ValueChanger from(EstablishmentType establishmentType) {
        return strategies.get(establishmentType.name());
    }

}
