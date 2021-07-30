package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionOperationStrategy {

    private final ApplicationContext context;
    private final Map<CardType, TransactionOperation> transactionOperationMap = new LinkedHashMap<>();

    @PostConstruct
    public void load() {
        transactionOperationMap.clear();
        context.getBeansOfType(TransactionOperation.class).values()
                .forEach(it -> transactionOperationMap.put(it.getCardType(), it));
    }

    public BigDecimal calculate(BigDecimal value, CardType type) {
        return transactionOperationMap.get(type).calculate(value);
    }
}
