package br.com.alelo.consumer.consumerpat.application;

import br.com.alelo.consumer.consumerpat.common.UseCase;
import br.com.alelo.consumer.consumerpat.domain.DrugStoreEstablishment;
import br.com.alelo.consumer.consumerpat.domain.Establishment;
import br.com.alelo.consumer.consumerpat.domain.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.FoodEstablishment;
import br.com.alelo.consumer.consumerpat.domain.FuelEstablishment;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeNotFoundException;

@UseCase
public class EstablishmentFactory {
  
  public Establishment getInstance(final Integer establishmentType) {

      switch (EstablishmentType.getByValue(establishmentType)) {
        case FOOD: {
          return new FoodEstablishment();
        }
        case DRUGSTORE: {
          return new DrugStoreEstablishment();
        }
        case FUEL: {
          return new FuelEstablishment();
        }
        default:
          throw new EstablishmentTypeNotFoundException();
      }
  }
}
