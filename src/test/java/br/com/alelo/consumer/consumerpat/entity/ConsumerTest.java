package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.models.Compra;
import br.com.alelo.consumer.consumerpat.models.CompraFactoryTest;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ConsumerTest {

  @Test
  public void deveAtualizarConsumer() {

    Consumer old = ConsumerFactoryTest.consumerWithId(1);
    Consumer atualizado = ConsumerFactoryTest.consumerUpdate(1, "Atualizado");

    old.update(atualizado);

    Assertions.assertEquals(old.getName(), atualizado.getName());
  }

  @ParameterizedTest
  @MethodSource("valoresParaCreditarTest")
  public void deveCreditar(Integer cartao, BigDecimal valor) {

    Consumer consumer = ConsumerFactoryTest.consumerWithId(1);

    consumer.creditar(cartao, valor);

  }

  @ParameterizedTest
  @MethodSource("valoresParaDebitarTest")
  public void deveDebitar(Integer cartao, Integer establishmentType, BigDecimal valor) {

    Compra compra = CompraFactoryTest.criar(establishmentType);
    Consumer consumer = ConsumerFactoryTest.consumerWithId(1);

    consumer.debitar(cartao, compra, valor);

  }

  private static Stream<Arguments> valoresParaCreditarTest() {
    return Stream.of(
        Arguments.of(1, BigDecimal.TEN),
        Arguments.of(2, BigDecimal.valueOf(20)),
        Arguments.of(3, BigDecimal.valueOf(30))
    );
  }

  private static Stream<Arguments> valoresParaDebitarTest() {
    return Stream.of(
        Arguments.of(1, 1, BigDecimal.TEN),
        Arguments.of(3, 2, BigDecimal.TEN),
        Arguments.of(2, 3, BigDecimal.TEN)
    );
  }

}
