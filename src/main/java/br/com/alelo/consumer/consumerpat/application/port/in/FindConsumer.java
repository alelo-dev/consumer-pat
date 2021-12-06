package br.com.alelo.consumer.consumerpat.application.port.in;

import java.util.List;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

public interface FindConsumer {

  public List<Consumer> all();
}
