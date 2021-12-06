package br.com.alelo.consumer.consumerpat.application;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.com.alelo.consumer.consumerpat.application.FindConsumerUseCase;
import br.com.alelo.consumer.consumerpat.application.port.out.FindConsumerPort;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

public class FindConsumerUseCaseTest {
  
  @Mock
  private FindConsumerPort findConsumerPort;

  private FindConsumerUseCase findConsumerUseCase;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    findConsumerUseCase = new FindConsumerUseCase(findConsumerPort);
  }

  @Test
  public void findSuccess() {

    var consumer = Consumer.builder().name("Test").build();
    var consumers = new ArrayList<Consumer>();
    
    consumers.add(consumer);

    when(findConsumerPort.all()).thenReturn(consumers);

    var consumersReturned = findConsumerUseCase.all();

    Mockito.verify(findConsumerPort, Mockito.atLeastOnce()).all();
    assertTrue(consumersReturned.size() == 1);

  }
}
