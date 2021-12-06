package br.com.alelo.consumer.consumerpat.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodEstablishmentTest {

  private FoodEstablishment foodEstablishment;

  @BeforeEach
  void init() {
    foodEstablishment = new FoodEstablishment();
  }

  @Test
  public void getCardType() {
    assertEquals(CardType.FOOD, foodEstablishment.getCardType());
  }

  @Test
  public void calculatePurchaseValue() {

    var purchaseValue = foodEstablishment.calculatePurchaseValue(10.0);
    assertEquals(purchaseValue, 9.0);
  }
}
