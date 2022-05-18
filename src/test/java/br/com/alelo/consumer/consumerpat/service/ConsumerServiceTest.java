package br.com.alelo.consumer.consumerpat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.constants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@DisplayName("Test for ConsumerService")
@ExtendWith(SpringExtension.class)
public class ConsumerServiceTest {

  @InjectMocks
  private ConsumerService service;

  @Mock
  private ConsumerRepository repositoryMock;

  @Mock
  private ExtractService extractService;

  @Mock
  private ExtractRepository extractRepositoryMock;

  private Consumer consumerSaved;
  private Consumer consumerToUpdate;
  private Consumer consumerToBeSaved;
  private Consumer consumer2;
  // private Extract extractToBeSaved;
  private Extract extractSaved;

  @BeforeEach
  private void setUp() throws ParseException {
    consumer2 = Consumer.builder()
        .name("João")
        .documentNumber("12345678901")
        .birthDate(new Date())
        .foodCardNumber(123456789)
        .foodCardBalance(100.0)
        .fuelCardNumber(123456789)
        .fuelCardBalance(100.0)
        .drugstoreCardBalance(100.0)
        .build();

    consumerToUpdate = Consumer.builder()
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
        .id(369)
        .build();

    consumerSaved = Consumer.builder()
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
        .id(369)
        .build();

    consumerToBeSaved = Consumer.builder()
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
    PageImpl<Consumer> consumerPage = new PageImpl<>(List.of(consumerSaved));
    consumerPage.and(consumer2);
    consumerPage.and(consumerToBeSaved);

    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(consumerPage);
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findById(ArgumentMatchers.any(Integer.class)))
        .thenReturn(Optional.of(consumerSaved));

    BDDMockito.when(extractRepositoryMock.save(ArgumentMatchers.any(Extract.class))).thenReturn(extractSaved);
  }

  @Test
  void testGetConsumerListGreaterThanZero() {
    Page<Consumer> consumerList = service.consumerList(PageRequest.of(0, 10));
    Assertions.assertThat(consumerList).isNotNull();
    Assertions.assertThat(consumerList.getSize()).isGreaterThan(0);
  }

  @Test
  void testGetConsumerListGreaterThen() {
    BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
        .thenReturn(new PageImpl<>(List.of()));
    Page<Consumer> consumerList = service.consumerList(PageRequest.of(0, 10));
    Assertions.assertThat(consumerList).isNotNull();
    Assertions.assertThat(consumerList.getSize()).isEqualTo(0);
  }

  @Test
  void testCreateConsumer() {
    Consumer consumerSaved = service.createConsumer(this.consumerToBeSaved);
    Assertions.assertThat(consumerSaved).isNotNull();
    Assertions.assertThat(consumerSaved.getId()).isEqualTo(369);
  }

  @Test
  void testSetFoodCardbalance100() {
    // BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findByFoodCardNumber(ArgumentMatchers.any(Integer.class))).thenReturn(consumerSaved);
    service.setCardbalance(11223344, 100);

    Assertions.assertThat(consumerSaved).isNotNull();
    Assertions.assertThat(consumerSaved.getFoodCardBalance()).isEqualTo(100);
  }

  @Test
  void testSetFuelCardbalance500() {
    // BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findByFuelCardNumber(ArgumentMatchers.any(Integer.class))).thenReturn(consumerSaved);
    service.setCardbalance(753951, 500);

    Assertions.assertThat(consumerSaved).isNotNull();
    Assertions.assertThat(consumerSaved.getFuelCardBalance()).isEqualTo(500);
  }

  @Test
  void testSetDugsStoreCardbalance500() {
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findByDrugstoreNumber(ArgumentMatchers.any(Integer.class)))
        .thenReturn(consumerSaved);
    service.setCardbalance(123485, 200);

    Assertions.assertThat(consumerSaved).isNotNull();
    Assertions.assertThat(consumerSaved.getDrugstoreCardBalance()).isEqualTo(200);
  }

  @Test
  void testUpdateConsumer() {
    Consumer consumerSaved = service.createConsumer(this.consumerToBeSaved);
    Assertions.assertThat(consumerSaved).isNotNull();
    consumerToUpdate.setFoodCardBalance(123);
    consumerToUpdate.setFuelCardBalance(321);
    consumerToUpdate.setDrugstoreCardBalance(222);

    Consumer consumerUpdated = service.updateConsumer(consumerToUpdate);
    Assertions.assertThat(consumerUpdated).isNotNull();
    Assertions.assertThat(consumerUpdated.getFoodCardBalance()).isEqualTo(consumerSaved.getFoodCardBalance());
    Assertions.assertThat(consumerUpdated.getFuelCardBalance()).isEqualTo(consumerSaved.getFuelCardBalance());
    Assertions.assertThat(consumerUpdated.getDrugstoreCardBalance()).isEqualTo(consumerSaved.getDrugstoreCardBalance());
  }

  @Test
  void testBuy() throws Exception {
    consumerSaved.setFoodCardBalance(100);
    consumerSaved.setFuelCardBalance(500);
    consumerSaved.setDrugstoreCardBalance(50);
    BDDMockito.when(repositoryMock.findByFuelCardNumber(ArgumentMatchers.any(Integer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findByFoodCardNumber(ArgumentMatchers.any(Integer.class))).thenReturn(consumerSaved);
    BDDMockito.when(repositoryMock.findByDrugstoreNumber(ArgumentMatchers.any(Integer.class)))
        .thenReturn(consumerSaved);

    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);

    service.setCardbalance(consumerSaved.getFoodCardNumber(), 100);
    service.setCardbalance(consumerSaved.getFuelCardNumber(), 500);
    service.setCardbalance(consumerSaved.getDrugstoreNumber(), 50);
    double foodValue = 32.50;
    double foodBalance = consumerSaved.getFoodCardBalance();
    double fuelValue = 350.50;
    double fuelBalance = consumerSaved.getFuelCardBalance();
    double drugstoreValue = 19.50;
    double drugstoreBalance = consumerSaved.getDrugstoreCardBalance();
    Double cashback = (foodValue / 100) * 10;
    Double tax = (fuelValue / 100) * 35;
    service.buy(EstablishmentType.FOOD_CARD, "Estabelecimento", consumerSaved.getFoodCardNumber(), "Marmita",
        foodValue);
    service.buy(EstablishmentType.FUEL_CARD, "Posto Shell", consumerSaved.getFuelCardNumber(), "Gasolina V-Power",
        fuelValue);
    service.buy(EstablishmentType.DRUGSTORE_CARD, "Farmacia Nissei", consumerSaved.getDrugstoreNumber(), "Nimesulida",
        drugstoreValue);

    Assertions.assertThat(consumerSaved.getFoodCardBalance())
        .isEqualTo((foodBalance - foodValue) + cashback);
    Assertions.assertThat(consumerSaved.getFuelCardBalance()).isEqualTo(fuelBalance - (fuelValue + tax));
    Assertions.assertThat(consumerSaved.getDrugstoreCardBalance()).isEqualTo(drugstoreBalance - drugstoreValue);
  }
}
