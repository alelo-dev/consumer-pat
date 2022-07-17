package br.com.alelo.consumer.consumerpat.service.strategy.establishment;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fuelEstablishmentStrategy")
public class FuelEstablishmentStrategy implements EstablishmentStrategy {

    @Override
    public double calcValueToDeb(double value) {
        Double tax  = (value / 100) * 35;
        value = value + tax;
        return value;
    }
}
