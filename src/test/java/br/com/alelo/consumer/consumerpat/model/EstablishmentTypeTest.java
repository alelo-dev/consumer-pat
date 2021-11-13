package br.com.alelo.consumer.consumerpat.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstablishmentTypeTest {

  @Test
  @DisplayName("Deve calcular o desconto de Alimentação com sucesso.")
  public void TypeFoodDiscountWithSuccessTest() {
    Double totalDiscount = 90D;
    Double totalValue = 100D;
    Double result = EstablishmentType.FOOD.paymentCalculator(totalValue);
    assertEquals(totalDiscount, result);
  }

  @Test
  @DisplayName("Deve calcular o desconto de Farmácia com sucesso.")
  public void TypeDrugStoreDiscountWithSuccessTest() {
    Double totalDiscount = 100D;
    Double totalValue = 100D;
    Double result = EstablishmentType.DRUG_STORE.paymentCalculator(totalValue);
    assertEquals(totalDiscount, result);
  }

  @Test
  @DisplayName("Deve calcular o acréscimo de Posto de Combustível com sucesso.")
  public void TypeFuelDiscountWithSuccessTest() {
    Double totalAddition = 135D;
    Double totalValue = 100D;
    Double result = EstablishmentType.FUEL.paymentCalculator(totalValue);
    assertEquals(totalAddition, result);
  }

}