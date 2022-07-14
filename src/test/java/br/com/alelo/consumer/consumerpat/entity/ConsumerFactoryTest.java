package br.com.alelo.consumer.consumerpat.entity;

import java.time.LocalDate;

/**
 * @author renanravelli
 */
public class ConsumerFactoryTest {

  public static Consumer novoConsumer() {
    return new Consumer(null, "Consumer novo", 1, LocalDate.now(), 1, 1, 1, "teste@alelo.com.br",
        "1", 1,
        "tst", "BR", 1);
  }

  public static Consumer consumerWithId(Integer id) {
    return new Consumer(id, "Consumer existente", 1, LocalDate.now(), 1, 1, 1, "teste@alelo.com.br",
        "1", 1,
        "tst", "BR", 1);
  }

  public static Consumer consumerUpdate(Integer id, String nome) {
    return new Consumer(id, nome, 1, LocalDate.now(), 1, 1, 1, "teste@alelo.com.br",
        "1", 1,
        "tst", "BR", 1);
  }

}
