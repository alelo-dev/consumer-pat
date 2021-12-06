package br.com.alelo.consumer.consumerpat.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuelEstablishmentTest {

  private FuelEstablishment fuelEstablishment;

  @BeforeEach
  void init() {
    fuelEstablishment = new FuelEstablishment();
  }

  @Test
  public void getCardType() {
    assertEquals(CardType.FUEL, fuelEstablishment.getCardType());
  }

  @Test
  public void calculatePurchaseValue() {

    var purchaseValue = fuelEstablishment.calculatePurchaseValue(10.0);
    assertEquals(purchaseValue, 13.5);
  }
}
