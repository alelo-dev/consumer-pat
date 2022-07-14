package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartaoTest {

  @Test
  public void deveCreditar() {

    final var cartao = CartaoFactoryTest.criar();

    cartao.creditar(BigDecimal.TEN);

    Assertions.assertEquals(cartao.getBalance(), BigDecimal.valueOf(20));
  }

  @Test
  public void deveDebitar() {

    final var cartao = CartaoFactoryTest.criar();

    cartao.debitar(BigDecimal.TEN);

    Assertions.assertEquals(cartao.getBalance(), BigDecimal.ZERO);

  }

}
