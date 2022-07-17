package br.com.alelo.consumer.consumerpat.service.strategy.establishment;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Primary
@Service("foodEstablishmentStrategy")
public class FoodEstablishmentStrategy implements EstablishmentStrategy {

    @Override
    public double calcValueToDeb(double value) {
        Double cashback  = (value / 100) * 10;
        value = value - cashback;
        return value;
    }
}
