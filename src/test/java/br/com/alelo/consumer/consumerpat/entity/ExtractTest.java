package br.com.alelo.consumer.consumerpat.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import java.util.Date;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExtractTest {

  @Test
  public void ExtractEntityTest() {
    Extract extract = new Extract("establishmentName", "productDescription", new Date(), 123456789, 1000);

    extract.setEstablishmentNameId(1);
    extract.setEstablishmentName("Teste do teste");
    extract.setProductDescription("Produto beta");
    extract.setDateBuy(new Date());
    extract.setCardNumber(123456789);
    extract.setValue(1000);

    assertNotNull(extract.getEstablishmentNameId());
    assertNotNull(extract.getEstablishmentName());
    assertNotNull(extract.getProductDescription());
    assertNotNull(extract.getDateBuy());
    assertNotNull(extract.getCardNumber());
    assertNotNull(extract.getValue());
  }
}