package br.com.alelo.consumer.consumerpat.application;

import java.util.Objects;
import br.com.alelo.consumer.consumerpat.application.port.in.UpdateConsumer;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateConsumerPort;
import br.com.alelo.consumer.consumerpat.common.UseCase;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.exception.InvalidConsumerException;

@UseCase
public class UpdateConsumerUseCase implements UpdateConsumer{

  private UpdateConsumerPort updateConsumerPort;

  public UpdateConsumerUseCase(final UpdateConsumerPort updateConsumerPortt) {
    this.updateConsumerPort = updateConsumerPortt;
  }

  @Override
  public Consumer update(final Consumer consumer) {
    
      if (!isValidConsumer(consumer)) {
        throw new InvalidConsumerException();
      }
      return updateConsumerPort.update(consumer);
  }
  
  private boolean isValidConsumer(final Consumer consumer) {
    return Objects.isNull(consumer.getCards());
  }
}
