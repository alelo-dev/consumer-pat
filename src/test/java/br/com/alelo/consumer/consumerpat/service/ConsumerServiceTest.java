package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConsumerServiceTest {

  @Autowired private ConsumerService consumerService;

  @Test
  void createConsumerSuccess() {
    Consumer consumer =
        new Consumer(
            1,
            "newConsumer",
            7777,
            LocalDateTime.now(),null,
            null,
            null);
    consumerService.save(consumer);
    assertEquals(7777, consumer.getDocumentNumber());
  }

  @Test
  void consumerUpdateSuccess() {
    Consumer consumer =
            new Consumer(
                    1,
                    "newConsumer",
                    7777,
                    LocalDateTime.now(),
                    null,
                    null,
                    null);
    consumerService.save(consumer);
    Consumer consumerUpdated =
        new Consumer(1, "newConsumerchange", 7777, LocalDateTime.now(), null, null, null);
    consumerService.update(1, consumerUpdated);
    assertEquals("newConsumerchange", consumerUpdated.getName());
  }
}
