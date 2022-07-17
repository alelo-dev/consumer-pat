package br.com.alelo.consumer.consumerpat.service.strategy.establishment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentFactory {

    @Autowired
    @Qualifier("foodEstablishmentStrategy")
    private EstablishmentStrategy foodEstablishmentStrategy;

    @Autowired
    @Qualifier("fuelEstablishmentStrategy")
    private EstablishmentStrategy fuelEstablishmentStrategy;

    @Autowired
    @Qualifier("drugstoreEstablishmentStrategy")
    private EstablishmentStrategy drugstoreEstablishmentStrategy;

    public EstablishmentStrategy getEstablishent(int establishmentType) {
        if (establishmentType==1) {
            return foodEstablishmentStrategy;
        } else if (establishmentType == 2) {
            return fuelEstablishmentStrategy;
        } else if (establishmentType == 3) {
            return drugstoreEstablishmentStrategy;
        }

        return null;    // lancar exception
    }
}
