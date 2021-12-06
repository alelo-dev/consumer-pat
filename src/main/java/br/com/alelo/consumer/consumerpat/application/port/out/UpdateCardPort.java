package br.com.alelo.consumer.consumerpat.application.port.out;

import br.com.alelo.consumer.consumerpat.domain.Card;

public interface UpdateCardPort {

  public Card updateBalance(final Card card);
}
