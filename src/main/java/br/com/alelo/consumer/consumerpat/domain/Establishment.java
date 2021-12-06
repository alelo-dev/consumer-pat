package br.com.alelo.consumer.consumerpat.domain;

public interface Establishment {

  public CardType getCardType();

  public Double calculatePurchaseValue(final Double value);

}
