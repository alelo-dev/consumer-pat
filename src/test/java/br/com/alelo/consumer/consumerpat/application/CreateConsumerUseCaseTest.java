package br.com.alelo.consumer.consumerpat.application;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.com.alelo.consumer.consumerpat.application.CreateConsumerUseCase;
import br.com.alelo.consumer.consumerpat.application.port.in.CreateConsumer;
import br.com.alelo.consumer.consumerpat.application.port.out.SaveConsumerPort;
import br.com.alelo.consumer.consumerpat.domain.Consumer;

public class CreateConsumerUseCaseTest {

  @Mock
  private SaveConsumerPort saveConsumerPort;

  private CreateConsumer createConsumerUseCase;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    createConsumerUseCase = new CreateConsumerUseCase(saveConsumerPort);
  }

  @Test
  public void createSuccess() {

    var consumer = Consumer.builder().name("Test").documentNumber(123).build();

    when(saveConsumerPort.save(Mockito.any())).thenReturn(Mockito.any());

    createConsumerUseCase.create(consumer);

    Mockito.verify(saveConsumerPort, Mockito.atLeastOnce()).save(Mockito.any());
    
  }

}
