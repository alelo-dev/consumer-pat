package br.com.alelo.consumer.consumerpat.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsumerTest {

  @Test
  public void deveAtualizarConsumer() {

    Consumer old = ConsumerFactoryTest.consumerWithId(1);
    Consumer atualizado = ConsumerFactoryTest.consumerUpdate(1, "Atualizado");

    old.update(atualizado);

    Assertions.assertEquals(old.getName(), atualizado.getName());
  }

}
