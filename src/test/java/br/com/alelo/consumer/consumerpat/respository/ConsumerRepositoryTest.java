package br.com.alelo.consumer.consumerpat.respository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@DataJpaTest
@DisplayName("Test for ConsumerRepository")
@ExtendWith(SpringExtension.class)
public class ConsumerRepositoryTest {

  @Autowired
  private ConsumerRepository repository;

  @BeforeEach
  private void setUp() throws ParseException {

    Consumer consumer = Consumer.builder()
        .name("João")
        .documentNumber("12345678901")
        .birthDate(new Date())
        .foodCardNumber(123456789)
        .foodCardBalance(100.0)
        .fuelCardNumber(123456789)
        .fuelCardBalance(100.0)
        .drugstoreCardBalance(100.0)
        .build();

    repository.save(consumer);

    Consumer consumer2 = Consumer.builder()
        .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1985"))
        .city("Pinhais")
        .country("Brasil")
        .documentNumber("12345678910")
        .drugstoreCardBalance(0)
        .drugstoreNumber(123485)
        .email("leandro.rauseo@email.com")
        .foodCardBalance(0)
        .foodCardNumber(11223344)
        .foodCardBalance(0)
        .foodCardNumber(74185293)
        .fuelCardBalance(0)
        .fuelCardNumber(753951)
        .mobilePhoneNumber("41 9912345678")
        .name("Leandro Rauseo")
        .number(642)
        .portalCode(8300100)
        .residencePhoneNumber("41 9912345678")
        .street("Rua Rio Paraná")
        .build();
    repository.save(consumer2);
  }

  @Test
  @DisplayName("test to find Consumer by Drugstore Card Number")
  void testFindByDrugstoreNumber() throws ParseException {
    Consumer consumer = repository.findByDrugstoreNumber(123485);
    Assertions.assertThat(consumer).isNotNull();
  }

  @Test
  @DisplayName("test when don't find Consumer by Drugstore Card Number")
  void testNotFindByDrugstoreNumber() throws ParseException {
    Consumer consumer = repository.findByDrugstoreNumber(9999868);
    Assertions.assertThat(consumer).isNull();
  }

  @Test
  @DisplayName("test to find Consumer by Food Card Number")
  void testFindByFoodCardNumber() throws ParseException {
    Consumer consumer = repository.findByFoodCardNumber(123456789);
    Assertions.assertThat(consumer).isNotNull();
  }

  @Test
  @DisplayName("test when don't find Consumer by Food Card Number")
  void testNotFindByFoodCardNumber() throws ParseException {
    Consumer consumer = repository.findByFoodCardNumber(999999);
    Assertions.assertThat(consumer).isNull();
  }

  @Test
  @DisplayName("test to find Consumer by Fuel Card Number")
  void testFindByFuelCardNumber() {
    Consumer consumer = repository.findByFuelCardNumber(123456789);
    Assertions.assertThat(consumer).isNotNull();
  }

  @Test
  @DisplayName("test when don't find Consumer by Fuel Card Number")
  void testNotFindByFuelCardNumber() {
    Consumer consumer = repository.findByFuelCardNumber(99885555);
    Assertions.assertThat(consumer).isNull();
  }
}
