package br.com.alelo.consumer.consumerpat.domain;

import java.util.Arrays;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeNotFoundException;

public enum EstablishmentType {

  FOOD(1), DRUGSTORE(2), FUEL(3);

  public final Integer value;

  private EstablishmentType(Integer value) {
    this.value = value;
  }

  public static final EstablishmentType getByValue(Integer value) {
    return Arrays.stream(EstablishmentType.values())
        .filter(establishmentType -> establishmentType.value.equals(value)).findFirst().orElseThrow(() -> new EstablishmentTypeNotFoundException());
  }
}