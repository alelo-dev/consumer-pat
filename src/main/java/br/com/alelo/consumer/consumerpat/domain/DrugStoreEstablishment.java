package br.com.alelo.consumer.consumerpat.domain;

public class DrugStoreEstablishment implements Establishment {

  @Override
  public CardType getCardType() {
    return CardType.DRUGSTORE;
  }

  @Override
  public Double calculatePurchaseValue(final Double value) {
    return value;
  }

}
