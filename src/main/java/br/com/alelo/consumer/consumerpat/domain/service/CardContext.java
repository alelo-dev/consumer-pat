package br.com.alelo.consumer.consumerpat.domain.service;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Builder
@NoArgsConstructor
public class CardContext {
    private Strategy strategy;

    public CardContext(Strategy strategy){
        this.strategy = strategy;
    }

    public BigDecimal creditValue(BigDecimal balance, BigDecimal value){
    return strategy.creditValue(balance, value);
    }

    public BigDecimal applyCashback(BigDecimal balance, BigDecimal value){
        return strategy.applyCashback(balance, value);
    }
 }
