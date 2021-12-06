package br.com.alelo.consumer.consumerpat.application.port.out;

import br.com.alelo.consumer.consumerpat.domain.Consumer;

public interface SaveConsumerPort {
  
  public Consumer save(final Consumer consumer);

}
