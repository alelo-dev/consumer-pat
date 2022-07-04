package br.com.alelo.consumer.consumerpat.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import java.util.Date;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConsumerTest {

  @Test
  public void consumerEntityTest() {
    Consumer consumer = new Consumer();

    consumer.setBirthDate(new Date());
    consumer.setCity("Maringá");
    consumer.setCountry("Brasil");
    consumer.setDocumentNumber(123456789);
    consumer.setDrugstoreCardBalance(1000);
    consumer.setDrugstoreNumber(1);
    consumer.setEmail("teste@teste.teste");
    consumer.setFoodCardBalance(1000);
    consumer.setFoodCardNumber(123456789);
    consumer.setFuelCardBalance(1);
    consumer.setFuelCardNumber(1);
    consumer.setMobilePhoneNumber(123456789);
    consumer.setName("Teste");
    consumer.setNumber(1);
    consumer.setPhoneNumber(123456789);
    consumer.setPortalCode(12345678);
    consumer.setResidencePhoneNumber(123456789);
    consumer.setStreet("Rua teste do teste, bairro teste numero teste");

    assertNotNull(consumer.getMobilePhoneNumber());
    assertNotNull(consumer.getResidencePhoneNumber());
    assertNotNull(consumer.getPhoneNumber());
    assertNotNull(consumer.getEmail());

    assertNotNull(consumer.getNumber());
    assertNotNull(consumer.getCity());
    assertNotNull(consumer.getCountry());
    assertNotNull(consumer.getPortalCode());
    assertNotNull(consumer.getFoodCardNumber());
    assertNotNull(consumer.getFuelCardNumber());
    assertNotNull(consumer.getFuelCardBalance());
    assertNotNull(consumer.getDrugstoreNumber());
    assertNotNull(consumer.getDrugstoreCardBalance());
  }
}