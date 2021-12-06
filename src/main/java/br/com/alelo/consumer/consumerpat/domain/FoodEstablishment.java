package br.com.alelo.consumer.consumerpat.domain;

public class FoodEstablishment implements Establishment {
  
  private final static Integer PERCENTAGEVALUE = 10;

  @Override
  public CardType getCardType() {
    return CardType.FOOD;
  }

  @Override
  public Double calculatePurchaseValue(final Double value) {
    
    var cashback = (value / 100) * PERCENTAGEVALUE;
    return value - cashback;
  }
}
