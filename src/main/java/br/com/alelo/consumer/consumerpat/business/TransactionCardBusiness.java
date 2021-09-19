package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.DtoBuy;

public interface TransactionCardBusiness {

  double value(DtoBuy dtoBuy);

  void saveCard(Consumer consumer);

  void setCard(Consumer consumer, Card card);

  boolean existsNumber(Consumer consumer);
}
