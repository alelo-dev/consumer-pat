package br.com.alelo.consumer.consumerpat.application.port.in;

import br.com.alelo.consumer.consumerpat.domain.Card;

public interface CreditAddition {
  
  public Card credit(final Integer cardNumber, final Double value);

}
