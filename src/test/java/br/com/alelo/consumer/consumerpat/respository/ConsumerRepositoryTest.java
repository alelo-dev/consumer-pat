package br.com.alelo.consumer.consumerpat.respository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.constants.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

@DataJpaTest
@DisplayName("Test for ConsumerRepository")
@ExtendWith(SpringExtension.class)
public class ConsumerRepositoryTest {

  @Autowired
  private ConsumerRepository consumerRepository;

  @Autowired
  private CardRepository cardRepository;

  @BeforeEach
  private void setUp() throws ParseException {

    Consumer consumer = Consumer.builder()
        .name("João")
        .documentNumber("12345678901")
        .birthDate(new Date())
        .build();

    consumerRepository.save(consumer);
    Card consumerCard = Card.builder()
        .cardNumber("123456789")
        .cardBalance(100.0)
        .cardType(CardTypeEnum.FOOD)
        .consumer(consumer)
        .active(true)
        .build();
    cardRepository.save(consumerCard);

    Consumer consumer2 = Consumer.builder()
        .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1985"))
        .city("Pinhais")
        .country("Brasil")
        .documentNumber("12345678910")
        .email("leandro.rauseo@email.com")
        .mobilePhoneNumber("41 9912345678")
        .name("Leandro Rauseo")
        .number(642)
        .portalCode(8300100)
        .residencePhoneNumber("41 9912345678")
        .street("Rua Rio Paraná")
        .build();
    consumerRepository.save(consumer2);

    Card consumerCard2 = Card.builder()
        .cardNumber("123333")
        .cardBalance(400.0)
        .cardType(CardTypeEnum.FUEL)
        .consumer(consumer2)
        .active(true)
        .build();
    cardRepository.save(consumerCard2);
  }

  @Test
  @DisplayName("test to find Consumer Card Number")
  void testFindByFoodCardNumber() throws ParseException {
    final Card card = cardRepository.findByCardNumberAndActiveTrue("123333")
        .orElseThrow(() -> new RuntimeException("Card not found"));

    Assertions.assertNotNull(card);
    Assertions.assertNotNull(card.getConsumer());
  }

  @Test
  @DisplayName("test when don't find Consumer Card Number")
  void testNotFindByFoodCardNumber() throws ParseException {

    RuntimeException thrown = Assertions.assertThrows(
        RuntimeException.class,
        () -> cardRepository.findByCardNumberAndActiveTrue("999999")
            .orElseThrow(() -> new RuntimeException("Not found")),
        "Not found");

    Assertions.assertTrue(thrown.getMessage().contains("Not found"));

  }

  @Test
  @DisplayName("test Find consumer by document number")
  void testAddConsumerCard() {
    Consumer consumer = consumerRepository.findByDocumentNumber("12345678910")
        .orElseThrow(() -> new RuntimeException("Consumer not found"));
    Assertions.assertNotNull(consumer);
  }
}
