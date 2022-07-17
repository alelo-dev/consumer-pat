package br.com.alelo.consumer.consumerpat.service.strategy.establishment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("drugstoreEstablishmentStrategy")
public class DrugstoreEstablishmentStrategy implements EstablishmentStrategy {

    @Override
    public double calcValueToDeb(double value) {
        return value;
    }
}
