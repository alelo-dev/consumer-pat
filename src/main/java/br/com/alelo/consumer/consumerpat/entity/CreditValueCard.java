package br.com.alelo.consumer.consumerpat.entity;

import org.springframework.stereotype.Component;

@Component
public class CreditValueCard {

    public Consumer effect(int numberCard, double value, Card card){
        return card.CreditValue(numberCard, value);
    }
}
