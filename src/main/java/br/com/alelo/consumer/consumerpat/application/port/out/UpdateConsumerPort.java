package br.com.alelo.consumer.consumerpat.application.port.out;

import br.com.alelo.consumer.consumerpat.domain.Consumer;

public interface UpdateConsumerPort {

  public Consumer update(final Consumer consumer);
}
