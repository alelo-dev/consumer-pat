package br.com.alelo.consumer.consumerpat.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrugStoreEstablishmentTest {

  private DrugStoreEstablishment drugStoreEstablishment;

  @BeforeEach
  void init() {
    drugStoreEstablishment = new DrugStoreEstablishment();
  }

  @Test
  public void getCardType() {
    assertEquals(CardType.DRUGSTORE, drugStoreEstablishment.getCardType());
  }

  @Test
  public void calculatePurchaseValue() {

    var purchaseValue = drugStoreEstablishment.calculatePurchaseValue(10.0);
    assertEquals(purchaseValue, 10.0);
  }
}
