package br.com.alelo.consumer.consumerpat.domain.calculatecard;

import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentType;

public interface CalculateCardFactory {

    CalculateCard createCalculate(EstablishmentType establishmentType);
}
