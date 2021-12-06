package br.com.alelo.consumer.consumerpat.application.port.out;

import br.com.alelo.consumer.consumerpat.domain.Card;

public interface FindCardPort {
  
  public Card find(final Integer number);

}
