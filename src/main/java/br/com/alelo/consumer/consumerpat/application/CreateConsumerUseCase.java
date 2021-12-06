package br.com.alelo.consumer.consumerpat.application;

import br.com.alelo.consumer.consumerpat.application.port.in.CreateConsumer;
import br.com.alelo.consumer.consumerpat.application.port.out.SaveConsumerPort;
import br.com.alelo.consumer.consumerpat.common.UseCase;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

@UseCase
public class CreateConsumerUseCase implements CreateConsumer{
  
  private SaveConsumerPort saveConsumerPort;

  public CreateConsumerUseCase(final SaveConsumerPort saveConsumerPort) {
    this.saveConsumerPort = saveConsumerPort;
  }

  @Override
  public Consumer create(final Consumer consumer) {
      return saveConsumerPort.save(consumer);
  }

}
