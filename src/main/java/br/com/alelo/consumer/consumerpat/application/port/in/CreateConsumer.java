package br.com.alelo.consumer.consumerpat.application.port.in;

import br.com.alelo.consumer.consumerpat.domain.Consumer;

public interface CreateConsumer {

  public Consumer create(final Consumer consumer);

}
