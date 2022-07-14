package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerFactoryTest;
import br.com.alelo.consumer.consumerpat.exceptions.NotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceImplTest {

  @InjectMocks
  private ConsumerServiceImpl consumerService;

  @Mock
  private ConsumerRepository consumerRepository;

  @Mock
  private ExtractRepository extractRepository;

  @Test
  public void deveCadastrarConsumer() {

    Consumer consumer = ConsumerFactoryTest.novoConsumer();

    Mockito.when(consumerRepository.save(Mockito.any())).thenReturn(consumer);

    Consumer consumerCadastrado = this.consumerService.cadastrar(consumer);

    Assertions.assertThat(consumerCadastrado).isEqualTo(consumer);
  }

  @Test
  public void deveAtualizarConsumer() {

    Consumer consumer = ConsumerFactoryTest.novoConsumer();
    Consumer consumerExistente = ConsumerFactoryTest.consumerWithId(1);

    Mockito.when(consumerRepository.findById(Mockito.any()))
        .thenReturn(Optional.of(consumerExistente));
    Mockito.when(consumerRepository.save(Mockito.any())).thenReturn(consumerExistente);

    Consumer consumerAtualizado = this.consumerService.atualizar(consumerExistente.getId(),
        consumer);

    Assertions.assertThat(consumerAtualizado.getName()).isEqualTo(consumer.getName());
  }

  @Test
  public void deveLancarNotFoundExceptionAoAtualizarConsumer() {

    Consumer consumer = ConsumerFactoryTest.novoConsumer();
    Consumer consumerExistente = ConsumerFactoryTest.consumerWithId(1);

    Assertions.assertThatThrownBy(
            () -> this.consumerService.atualizar(consumerExistente.getId(), consumer))
        .isInstanceOf(NotFoundException.class);
  }

}
