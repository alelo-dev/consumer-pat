package br.com.alelo.consumer.consumerpat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConsumerEntityTest {

  @Test
  public void consumerEntityTest() {
    Consumer input = new Consumer();

    input.setMobilePhoneNumber("+55 81 9 8879-9043");
    input.setResidencePhoneNumber("+55 81 3525-3526");
    input.setPhoneNumber("+55 81 3525-3526");
    input.setEmail("hilquiaswpc10@outlook.com");
    input.setStreet("Rua Luiz Bezerra De SOuza");
    input.setNumber("50");
    input.setCity("Sao Lourenço da Mata");
    input.setCountry("Brazil");
    input.setPortalCode("54735-815");
    input.setFoodCardNumber(501456995444789l);
    input.setFoodCardBalance(291.50);
    input.setFuelCardNumber(401456995444785l);
    input.setFuelCardBalance(25699.00);
    input.setDrugstoreNumber(258);
    input.setDrugstoreCardBalance(58879.00);

    assertNotNull(input.getMobilePhoneNumber());
    assertNotNull(input.getResidencePhoneNumber());
    assertNotNull(input.getPhoneNumber());
    assertNotNull(input.getEmail());

    assertNotNull(input.getNumber());
    assertNotNull(input.getCity());
    assertNotNull(input.getCountry());
    assertNotNull(input.getPortalCode());
    assertNotNull(input.getFoodCardNumber());
    assertNotNull(input.getFuelCardNumber());
    assertNotNull(input.getFuelCardBalance());
    assertNotNull(input.getDrugstoreNumber());
    assertNotNull(input.getDrugstoreCardBalance());
  }
}
