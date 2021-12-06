package br.com.alelo.consumer.consumerpat.application;

import java.util.List;
import br.com.alelo.consumer.consumerpat.application.port.in.FindConsumer;
import br.com.alelo.consumer.consumerpat.application.port.out.FindConsumerPort;
import br.com.alelo.consumer.consumerpat.common.UseCase;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

@UseCase
public class FindConsumerUseCase implements FindConsumer {
  
  private FindConsumerPort findConsumerPort;

  public FindConsumerUseCase(final FindConsumerPort findConsumerPort) {
    this.findConsumerPort = findConsumerPort;
  }

  @Override
  public List<Consumer> all() {
    return findConsumerPort.all();
  }

}
