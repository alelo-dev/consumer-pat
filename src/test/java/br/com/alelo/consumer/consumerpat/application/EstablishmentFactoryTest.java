package br.com.alelo.consumer.consumerpat.application;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import br.com.alelo.consumer.consumerpat.application.EstablishmentFactory;
import br.com.alelo.consumer.consumerpat.domain.DrugStoreEstablishment;
import br.com.alelo.consumer.consumerpat.domain.FoodEstablishment;
import br.com.alelo.consumer.consumerpat.domain.FuelEstablishment;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeNotFoundException;

public class EstablishmentFactoryTest {

  private EstablishmentFactory establishmentFactory;


  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    establishmentFactory = new EstablishmentFactory();
  }


  @Test
  public void getFoodEstablishment() {

    var establishment = establishmentFactory.getInstance(1);

    assertThat(establishment).isInstanceOf(FoodEstablishment.class);

  }

  @Test
  public void getFuelEstablishment() {

    var establishment = establishmentFactory.getInstance(3);

    assertThat(establishment).isInstanceOf(FuelEstablishment.class);

  }

  @Test
  public void getDrugStoreEstablishment() {

    var establishment = establishmentFactory.getInstance(2);

    assertThat(establishment).isInstanceOf(DrugStoreEstablishment.class);

  }

  @Test
  public void getEstablishmentTypeNotFoundException() {

    Assertions.assertThrows(EstablishmentTypeNotFoundException.class, () -> {
      establishmentFactory.getInstance(4);
    });
  }

}
