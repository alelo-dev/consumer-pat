package br.com.alelo.consumer.consumerpat.domain.service;

import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
public class CardContext {
    private Strategy strategy;

    public CardContext(Strategy strategy){
        this.strategy = strategy;
    }

    public Double creditValue(Double balance, Double value){
    return strategy.creditValue(balance, value);
    }

    public Double applyCashback(Double balance, Double value){
        return strategy.applyCashback(balance, value);
    }
 }
