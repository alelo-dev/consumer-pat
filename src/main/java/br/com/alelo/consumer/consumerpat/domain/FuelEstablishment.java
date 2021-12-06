package br.com.alelo.consumer.consumerpat.domain;

public class FuelEstablishment implements Establishment {
  
  private final static Integer PERCENTAGEVALUE = 35;

  @Override
  public CardType getCardType() {
    return CardType.FUEL;
  }

  @Override
  public Double calculatePurchaseValue(final Double value) {
    var tax = (value / 100) * PERCENTAGEVALUE;
    return value + tax;
  }

}
