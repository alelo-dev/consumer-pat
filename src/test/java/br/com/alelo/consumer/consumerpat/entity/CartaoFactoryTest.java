package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;

public class CartaoFactoryTest {

  public static Cartao criar() {
    Consumer consumer = ConsumerFactoryTest.novoConsumer();
    return new Cartao(1, CartaoTipo.DRUGSTORE, 1, BigDecimal.TEN, consumer);
  }

}
