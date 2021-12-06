package br.com.alelo.consumer.consumerpat.application.port.out;

import java.util.List;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

public interface FindConsumerPort {

  public List<Consumer> all();
}
