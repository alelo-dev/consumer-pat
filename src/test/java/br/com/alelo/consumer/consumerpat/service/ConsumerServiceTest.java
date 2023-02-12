package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;

@SpringBootTest
public class ConsumerServiceTest {

  @InjectMocks
  private ConsumerService consumerService;

  @Mock
  private ConsumerRepository consumerRepository;

  @MockBean
  private CardOperationFactory cardOperationFactory;

  @Mock
  private ExtractService extractService;

  private Consumer consumerCreate;

  @BeforeEach
  public void initEach(){

    MockitoAnnotations.openMocks(this);

    consumerCreate = Consumer.builder()
            .identifier(null)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .foodCardNumber(999999999999999999L)
            .foodCardBalance(BigDecimal.ZERO)
            .fuelCardBalance(BigDecimal.ZERO)
            .drugstoreCardBalance(BigDecimal.ZERO)
            .build();
  }

  @Test
  public void shouldDebitCard() {

    CardTypeEnum establishmentType = CardTypeEnum.Food;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .foodCardNumber(999999999999999999L)
            .foodCardBalance(new BigDecimal(300))
            .build();


    FoodCard foodCard = new FoodCard(consumerRepository);
    FoodCard foodCardSpy = spy(foodCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(foodCardSpy);
    Mockito.when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    try {
      consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
              productDescription, value);
    }catch (Exception e) {
      fail("Should not have thrown any exception. Exception: " + e.getMessage());
    }

  }

  @Test
  public void shouldApply10PercentDiscountWhenDebitWithFoodCard() {

    CardTypeEnum establishmentType = CardTypeEnum.Food;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .foodCardNumber(999999999999999999L)
            .foodCardBalance(new BigDecimal(300))
            .build();


    FoodCard foodCard = new FoodCard(consumerRepository);
    FoodCard foodCardSpy = spy(foodCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(foodCardSpy);
    Mockito.when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    Consumer consumer = consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
            productDescription, value);

    assertThat(consumer.getFoodCardBalance(), comparesEqualTo(new BigDecimal("209.55")));

  }

  @Test
  public void shouldApply35PercentIncreaseWhenDebitWithFuelCard() {

    CardTypeEnum establishmentType = CardTypeEnum.Fuel;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .fuelCardNumber(999999999999999999L)
            .fuelCardBalance(new BigDecimal(300))
            .build();


    FuelCard fuelCard = new FuelCard(consumerRepository);
    FuelCard fuelCardSpy = spy(fuelCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(fuelCardSpy);
    Mockito.when(consumerRepository.findByFuelCardNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    Consumer consumer = consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
            productDescription, value);

    assertThat(consumer.getFuelCardBalance(), comparesEqualTo(new BigDecimal("164.33")));

  }

  @Test
  public void shouldNotChangeValueWhenDebitWithDrugstoreCard() {

    CardTypeEnum establishmentType = CardTypeEnum.DrugStore;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .drugstoreNumber(999999999999999999L)
            .drugstoreCardBalance(new BigDecimal(300))
            .build();


    DrugStoreCard drugStoreCard = new DrugStoreCard(consumerRepository);
    DrugStoreCard drugStoreCardSpy = spy(drugStoreCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(drugStoreCardSpy);
    Mockito.when(consumerRepository.findByDrugstoreNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    Consumer consumer = consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
            productDescription, value);

    assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("199.50")));

  }

  @Test
  public void shouldThrowExceptionWhenDebitWithFoodCardAndInsufficientBalance() {

    CardTypeEnum establishmentType = CardTypeEnum.Food;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .foodCardNumber(999999999999999999L)
            .foodCardBalance(BigDecimal.ZERO)
            .build();

    FoodCard foodCard = new FoodCard(consumerRepository);
    FoodCard foodCardSpy = spy(foodCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(foodCardSpy);
    Mockito.when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    try {
      consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
              productDescription, value);
      fail("Should have thrown an exception");
    }catch (Exception e) {
      assertThat(e.getMessage(), is("Insufficient balance"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenDebitWithFuelCardAndInsufficientBalance() {

    CardTypeEnum establishmentType = CardTypeEnum.Fuel;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .fuelCardNumber(999999999999999999L)
            .fuelCardBalance(BigDecimal.ZERO)
            .build();

    FuelCard fuelCard = new FuelCard(consumerRepository);
    FuelCard fuelCardSpy = spy(fuelCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(fuelCardSpy);
    Mockito.when(consumerRepository.findByFuelCardNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    try {
      consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
              productDescription, value);
      fail("Should have thrown an exception");
    }catch (Exception e) {
      assertThat(e.getMessage(), is("Insufficient balance"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenDebitWithDrugstoreCardAndInsufficientBalance() {

    CardTypeEnum establishmentType = CardTypeEnum.DrugStore;
    int establishmentNameId = 1;
    String establishmentName = "Test Establishment";
    long cardNumber = 999999999999L;
    String productDescription = "Product 1";
    BigDecimal value = new BigDecimal(100.50);

    Consumer consumerMock = Consumer.builder()
            .id(1)
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .drugstoreNumber(999999999999999999L)
            .drugstoreCardBalance(BigDecimal.ZERO)
            .build();

    DrugStoreCard drugstoreCard = new DrugStoreCard(consumerRepository);
    DrugStoreCard drugstoreCardSpy = spy(drugstoreCard);

    Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(drugstoreCardSpy);
    Mockito.when(consumerRepository.findByDrugstoreNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

    try {
      consumerService.debit(establishmentType, establishmentNameId, establishmentName, cardNumber,
              productDescription, value);
      fail("Should have thrown an exception");
    }catch (Exception e) {
      assertThat(e.getMessage(), is("Insufficient balance"));
    }

  }

  @Test
  public void shouldCreateConsumer() {

    try {
      consumerService.saveConsumer(consumerCreate);
    } catch(Exception e) {
      fail("Should not have thrown any exception. Exception: " + e.getMessage());
    }

  }

  @Test
  public void shouldUpdateConsumer() {

    consumerCreate.setIdentifier("1");

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerCreate));

    try {
      consumerService.saveConsumer(consumerCreate);
    } catch(Exception e) {
      fail("Should not have thrown any exception. Exception: " + e.getMessage());
    }

  }

  @Test
  public void shouldThrowExceptionWhenDocumentNumberAlreadyExists() {

    Mockito.when(consumerRepository.existsByDocumentNumber(anyLong(), anyInt())).thenReturn(true);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Consumer already exists"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenCardNumberNotInformed() {

    consumerCreate.setFoodCardNumber(null);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("At least one type of card must be informed"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenEqualsCardsNumbers() {

    consumerCreate.setFoodCardNumber(999999999999999999L);
    consumerCreate.setDrugstoreNumber(999999999999999999L);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Cards' numbers must differ from one another"));
    }

    consumerCreate.setFoodCardNumber(999999999999999999L);
    consumerCreate.setFuelCardNumber(999999999999999999L);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Cards' numbers must differ from one another"));
    }

    consumerCreate.setDrugstoreNumber(999999999999999999L);
    consumerCreate.setFuelCardNumber(999999999999999999L);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Cards' numbers must differ from one another"));
    }

    consumerCreate.setFoodCardNumber(999999999999999999L);
    consumerCreate.setFuelCardNumber(999999999999999999L);
    consumerCreate.setDrugstoreNumber(999999999999999997L);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Cards' numbers must differ from one another"));
    }


  }

  @Test
  public void shouldThrowExceptionWhenCardNumberAlreadyUsedByOtherConsumer() {

    Mockito.when(consumerRepository.existsByCardNumber(anyLong(), anyInt())).thenReturn(true);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(),
              is("Card number " + consumerCreate.getFoodCardNumber() + " previously used by another consumer"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateConsumerAndConsumerNotFound() {

    consumerCreate.setIdentifier("1");

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(null));

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Consumer not found"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateFoodCardNumberAndExistsExtract() {

    consumerCreate.setIdentifier("1");

    Consumer consumerUpdate = Consumer.builder()
            .id(1)
            .identifier("1")
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .foodCardNumber(111111111111111111L)
            .foodCardBalance(BigDecimal.ZERO)
            .build();

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerUpdate));
    Mockito.when(extractService.existsExtractByCardNumber(consumerUpdate.getFoodCardNumber()))
            .thenReturn(true);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Food Card number is already on the extract and cannot be changed"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateFoodCardNumberAndExistsBalance() {

    consumerCreate.setIdentifier("1");

    Consumer consumerUpdate = Consumer.builder()
            .id(1)
            .identifier("1")
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .foodCardNumber(111111111111111111L)
            .foodCardBalance(BigDecimal.ONE)
            .build();

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerUpdate));

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Food card number already has a balance and cannot be changed"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateFuelCardNumberAndExistsExtract() {

    consumerCreate.setIdentifier("1");
    consumerCreate.setFoodCardNumber(null);
    consumerCreate.setFuelCardNumber(999999999999999999L);

    Consumer consumerUpdate = Consumer.builder()
            .id(1)
            .identifier("1")
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .fuelCardNumber(111111111111111111L)
            .fuelCardBalance(BigDecimal.ZERO)
            .build();

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerUpdate));
    Mockito.when(extractService.existsExtractByCardNumber(consumerUpdate.getFuelCardNumber()))
            .thenReturn(true);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Fuel Card number is already on the extract and cannot be changed"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateFuelCardNumberAndExistsBalance() {

    consumerCreate.setIdentifier("1");
    consumerCreate.setFoodCardNumber(null);
    consumerCreate.setFuelCardNumber(999999999999999999L);

    Consumer consumerUpdate = Consumer.builder()
            .id(1)
            .identifier("1")
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .fuelCardNumber(111111111111111111L)
            .fuelCardBalance(BigDecimal.ONE)
            .build();

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerUpdate));

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Fuel card number already has a balance and cannot be changed"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateDrugstoreCardNumberAndExistsExtract() {

    consumerCreate.setIdentifier("1");
    consumerCreate.setFoodCardNumber(null);
    consumerCreate.setDrugstoreNumber(999999999999999999L);

    Consumer consumerUpdate = Consumer.builder()
            .id(1)
            .identifier("1")
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .drugstoreNumber(111111111111111111L)
            .drugstoreCardBalance(BigDecimal.ZERO)
            .build();

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerUpdate));
    Mockito.when(extractService.existsExtractByCardNumber(consumerUpdate.getDrugstoreNumber()))
            .thenReturn(true);

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Drugstore Card number is already on the extract and cannot be changed"));
    }

  }

  @Test
  public void shouldThrowExceptionWhenUpdateDrugstoreCardNumberAndExistsBalance() {

    consumerCreate.setIdentifier("1");
    consumerCreate.setFoodCardNumber(null);
    consumerCreate.setDrugstoreNumber(999999999999999999L);

    Consumer consumerUpdate = Consumer.builder()
            .id(1)
            .identifier("1")
            .name("Test Name")
            .documentNumber(9999999)
            .birthDate(LocalDate.now())
            .mobilePhoneNumber(11999999999L)
            .residencePhoneNumber(1199999999L)
            .phoneNumber(1199999999L)
            .email("test@gmail.com")
            .street("Rua Teste")
            .number (146)
            .city("Sao Paulo")
            .country("Brasil")
            .postalCode(3940030)
            .drugstoreNumber(111111111111111111L)
            .drugstoreCardBalance(BigDecimal.ONE)
            .build();

    Mockito.when(consumerRepository.findByIdentifier(anyString())).thenReturn(Optional.ofNullable(consumerUpdate));

    try {
      consumerService.saveConsumer(consumerCreate);
      fail("Should have thrown an exception");
    } catch(Exception e) {
      assertThat(e.getMessage(), is("Drugstore card number already has a balance and cannot be changed"));
    }

  }

  @Test
  public void shouldAddFoodCardBalanceCredit() {

    long cardNumber = 999999999999L;
    BigDecimal value = new BigDecimal("100.50");

    FoodCard foodCard = new FoodCard(consumerRepository);
    FoodCard foodCardSpy = spy(foodCard);

    Mockito.when(cardOperationFactory.findType(consumerCreate, cardNumber)).thenReturn(foodCardSpy);
    Mockito.when(consumerRepository.findByCardNumber(anyLong(), anyInt())).thenReturn(Optional.of(consumerCreate));

    Consumer consumer = consumerService.credit(cardNumber, value);

    assertThat(consumer.getFoodCardBalance(), comparesEqualTo(new BigDecimal("100.50")));

    value = new BigDecimal("56.71");
    consumer = consumerService.credit(cardNumber, value);

    assertThat(consumer.getFoodCardBalance(), comparesEqualTo(new BigDecimal("157.21")));

  }

  @Test
  public void shouldAddFuelCardBalanceCredit() {

    long cardNumber = 999999999999L;
    BigDecimal value = new BigDecimal("100.50");

    consumerCreate.setFoodCardNumber(null);
    consumerCreate.setFuelCardNumber(cardNumber);

    FuelCard fuelCard = new FuelCard(consumerRepository);
    FuelCard fuelCardSpy = spy(fuelCard);

    Mockito.when(cardOperationFactory.findType(consumerCreate, cardNumber)).thenReturn(fuelCardSpy);
    Mockito.when(consumerRepository.findByCardNumber(anyLong(), anyInt())).thenReturn(Optional.of(consumerCreate));

    Consumer consumer = consumerService.credit(cardNumber, value);

    assertThat(consumer.getFuelCardBalance(), comparesEqualTo(new BigDecimal("100.50")));

    value = new BigDecimal("56.71");
    consumer = consumerService.credit(cardNumber, value);

    assertThat(consumer.getFuelCardBalance(), comparesEqualTo(new BigDecimal("157.21")));

  }

  @Test
  public void shouldAddDrugstoreCardBalanceCredit() {

    long cardNumber = 999999999999L;
    BigDecimal value = new BigDecimal("100.50");

    consumerCreate.setFoodCardNumber(null);
    consumerCreate.setDrugstoreNumber(cardNumber);

    DrugStoreCard drugStoreCard = new DrugStoreCard(consumerRepository);
    DrugStoreCard drugStoreCardSpy = spy(drugStoreCard);

    Mockito.when(cardOperationFactory.findType(consumerCreate, cardNumber)).thenReturn(drugStoreCardSpy);
    Mockito.when(consumerRepository.findByCardNumber(anyLong(), anyInt())).thenReturn(Optional.of(consumerCreate));

    Consumer consumer = consumerService.credit(cardNumber, value);

    assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("100.50")));

    value = new BigDecimal("56.71");
    consumer = consumerService.credit(cardNumber, value);

    assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("157.21")));

  }

  @Test
  public void shouldThrowExceptionWhenAddCreditAndConsumerNotInformed() {

    long cardNumber = 999999999999L;
    BigDecimal value = new BigDecimal("100.50");

    Mockito.when(consumerRepository.findByCardNumber(anyLong(), anyInt())).thenReturn(Optional.ofNullable(null));

    try {
      Consumer consumer = consumerService.credit(cardNumber, value);
      fail("Should have thrown an exception");
    }catch (Exception e){
      assertThat(e.getMessage(), is("Consumer not found"));
    }

  }

}