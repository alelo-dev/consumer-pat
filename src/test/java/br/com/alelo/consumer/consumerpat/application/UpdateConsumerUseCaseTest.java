package br.com.alelo.consumer.consumerpat.application;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateConsumerPort;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.exception.InvalidConsumerException;

public class UpdateConsumerUseCaseTest {

  @Mock
  private UpdateConsumerPort updateConsumerPort;

  private UpdateConsumerUseCase updateConsumerUseCase;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    updateConsumerUseCase = new UpdateConsumerUseCase(updateConsumerPort);
  }

  @Test
  public void updateSuccess() {

    var consumer = Consumer.builder().name("Test").documentNumber(123).build();

    when(updateConsumerPort.update(Mockito.any())).thenReturn(Mockito.any());

    updateConsumerUseCase.update(consumer);

    Mockito.verify(updateConsumerPort, Mockito.atLeastOnce()).update(Mockito.any());
  }

  @Test
  public void invalidConsumer() {

    var cards = new ArrayList<Card>();
    var consumer = Consumer.builder().name("Test").documentNumber(123).cards(cards).build();

    Assertions.assertThrows(InvalidConsumerException.class, () -> {
      updateConsumerUseCase.update(consumer);
    });
  }

}
