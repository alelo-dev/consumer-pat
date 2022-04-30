package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsumerServiceTest {

	@Autowired
	private ConsumerService consumerController;

	@Autowired
	private ConsumerRepository repository;

	@Autowired
	private ExtractRepository extractRepository;

	private List<Consumer> consumersSaved;

	@BeforeAll
	public void setUp() {
		consumersSaved = getListOfConsumer();
		repository.saveAll(consumersSaved);
	}

	@Test
	public void contextLoads() {
		Assertions.assertThat(consumerController).isNotNull();
		Assertions.assertThat(repository).isNotNull();
		Assertions.assertThat(extractRepository).isNotNull();
	}

	@Test
	public void shouldListAllConsumers() {

		List<Consumer> consumersFind = consumerController.listAllConsumers();
		List<Consumer> consumersPersisted = repository.findAll();

		Assertions.assertThat(consumersFind.size()).isEqualTo(consumersPersisted.size());
	}

	@Test
	public void shouldCreateConsumer() {

		Consumer consumer = Consumer.builder()
				.name("Consumer 4")
				.documentNumber(44444444444L)
				.birthDate(LocalDate.of(1984, 01, 19))
				.mobilePhoneNumber(69991396661L)
				.residencePhoneNumber(6933000052L)
				.phoneNumber(6933000052L)
				.street("Rua Principal")
				.number("452L")
				.city("Cidade 4")
				.country("Brasil")
				.email("email4@gmail.com")
				.portalCode(99051720)
				.foodCardBalance(BigDecimal.TEN)
				.foodCardNumber(4444444444444444L)
				.fuelCardBalance(null)
				.fuelCardNumber(null)
				.drugstoreCardBalance(null)
				.drugstoreNumber(null)
				.build();

		consumerController.save(consumer);

		Assertions.assertThat(consumer.getId()).isNotNull();

	}

	@Test
	public void shouldDontCreateConsumerDuplicate() {

		Consumer consumer1 = consumersSaved.stream().findAny().get();
		Consumer consumer2 = new Consumer(consumer1.getName(), consumer1.getDocumentNumber());

		//It should be saved
		Assertions.assertThat(consumer1.getId()).isNotNull();

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			consumerController.save(consumer2);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(ConsumerAlreadyExistsException.class)
				.hasMessageContaining("Consumer couldnt have document number duplicate");
	}

	@Test
	public void shouldDontCreateConsumerFoodCardDuplicate() {

		Consumer consumer1 = consumersSaved.stream().findAny().get();
		Consumer consumer2 = new Consumer(consumer1.getName(), 2016615192L, consumer1.getFoodCardNumber(), null, null);

		//It should be saved
		Assertions.assertThat(consumer1.getId()).isNotNull();

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			consumerController.save(consumer2);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(ConsumerAlreadyExistsException.class)
				.hasMessageContaining("Consumer couldnt have food card duplicate");
	}

	@Test
	public void shouldDontCreateConsumerFuelCardDuplicate() {

		Consumer consumer1 = repository.findById(consumersSaved.get(1).getId()).get();
		Consumer consumer2 = new Consumer(consumer1.getName(), 2016615193L, null, consumer1.getFuelCardNumber(), null);

		//It should be saved
		Assertions.assertThat(consumer1.getId()).isNotNull();

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			consumerController.save(consumer2);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(ConsumerAlreadyExistsException.class)
				.hasMessageContaining("Consumer couldnt have fuel card duplicate");
	}

	@Test
	public void shouldDontCreateConsumerDrugstoreCardDuplicate() {

		Consumer consumer1 = repository.findById(consumersSaved.get(2).getId()).get();
		Consumer consumer2 = new Consumer(consumer1.getName(), 2016615194L, null, null, consumer1.getDrugstoreNumber());

		//It should be saved
		Assertions.assertThat(consumer1.getId()).isNotNull();

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			consumerController.save(consumer2);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(ConsumerAlreadyExistsException.class)
				.hasMessageContaining("Consumer couldnt have drugstore card duplicate");
	}

	@Test
	public void shouldUpdateConsumerWithoutChangeBalance() {

		Consumer consumerUpdate = repository.findById(consumersSaved.stream().findAny().get().getId()).get();
		//we are saving the balances to compare after the update
		BigDecimal foodCardBalance = consumerUpdate.getFoodCardBalance();
		BigDecimal fuelCardBalance = consumerUpdate.getFuelCardBalance();
		BigDecimal drugstoreCardBalance = consumerUpdate.getDrugstoreCardBalance();
		//everything is ok
		Assertions.assertThat(consumerUpdate.getId()).isNotNull();

		//Now we will set new values into the object
		String newName = "New Name";
		//set new name
		consumerUpdate.setName(newName);

		//now we will simulate to put new values in balances
		consumerUpdate.setDrugstoreCardBalance(BigDecimal.ZERO);
		consumerUpdate.setFoodCardBalance(BigDecimal.ZERO);
		consumerUpdate.setFuelCardBalance(BigDecimal.ZERO);

		//and we will send the objeto to change
		consumerController.update(consumerUpdate);

		//Now we will recover from DB to see how the balances are
		consumerUpdate = repository.findById(consumerUpdate.getId()).get();

		//comparing the balances dont have to be changed
		Assertions.assertThat(consumerUpdate.getName()).isEqualToIgnoringCase(newName);
		if(consumerUpdate.getFoodCardBalance() != null){
			Assertions.assertThat(consumerUpdate.getFoodCardBalance().setScale(2)).isEqualTo(foodCardBalance.setScale(2));
		}
		if(consumerUpdate.getFuelCardBalance() != null){
			Assertions.assertThat(consumerUpdate.getFuelCardBalance().setScale(2)).isEqualTo(fuelCardBalance.setScale(2));
		}
		if(consumerUpdate.getDrugstoreCardBalance() != null){
			Assertions.assertThat(consumerUpdate.getDrugstoreCardBalance().setScale(2)).isEqualTo(drugstoreCardBalance.setScale(2));
		}
	}

	@Test
	public void shouldSetBalanceOfConsumerFoodCard() {

		Consumer consumer1 = consumersSaved.get(0);
		Long foodCardNumber = consumer1.getFoodCardNumber();
		BigDecimal foodCardBalance = consumer1.getFoodCardBalance();
		BigDecimal newValueToFoodCardBalance = BigDecimal.TEN;

		consumerController.setBalance(foodCardNumber, newValueToFoodCardBalance);

		consumer1 = repository.findById(consumer1.getId()).get();

		Assertions.assertThat(consumer1.getFoodCardBalance().setScale(2)).isEqualTo(foodCardBalance.add(newValueToFoodCardBalance).setScale(2));

	}

	@Test
	public void shouldSetBalanceOfConsumerFuelCard() {

		Consumer consumer2 = consumersSaved.get(1);
		Long fuelCardNumber = consumer2.getFuelCardNumber();
		BigDecimal fuelCardBalance = consumer2.getFuelCardBalance();
		BigDecimal newValueToFuelCardBalance = BigDecimal.TEN;

		consumerController.setBalance(fuelCardNumber, newValueToFuelCardBalance);

		consumer2 = repository.findById(consumer2.getId()).get();

		Assertions.assertThat(consumer2.getFuelCardBalance().setScale(2)).isEqualTo(fuelCardBalance.add(newValueToFuelCardBalance).setScale(2));

	}

	@Test
	public void shouldSetBalanceOfConsumerDrugstoreCard() {

		Consumer consumer3 = consumersSaved.get(2);
		Long drugstoreCardNumber = consumer3.getDrugstoreNumber();
		BigDecimal drugstoreCardBalance = consumer3.getDrugstoreCardBalance();
		BigDecimal newValueToDrugstoreCardBalance = BigDecimal.TEN;

		consumerController.setBalance(drugstoreCardNumber, newValueToDrugstoreCardBalance);

		consumer3 = repository.findById(consumer3.getId()).get();

		Assertions.assertThat(consumer3.getDrugstoreCardBalance().setScale(2)).isEqualTo(drugstoreCardBalance.add(newValueToDrugstoreCardBalance).setScale(2));

	}

	@Test
	public void shouldDontSetBalanceOfNumberCardWrong() {

		Long drugstoreCardNumber = 545457784554L;
		BigDecimal newValueToDrugstoreCardBalance = BigDecimal.TEN;

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			consumerController.setBalance(drugstoreCardNumber, newValueToDrugstoreCardBalance);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(ConsumerNotFoundException.class)
				.hasMessageContaining("Consumer not found");
	}

	@Test
	public void shouldBuyWithFoodCardWith10PercentOfDiscount() {

		Consumer consumer1 = repository.findById(consumersSaved.get(0).getId()).get();
		Long foodCardNumber = consumer1.getFoodCardNumber();
		BigDecimal foodCardBalance = consumer1.getFoodCardBalance();
		BigDecimal valueSpended = BigDecimal.TEN;
		String establishmentName =  "Casa da Picanha";
		String productDescription = " 1k de Picanha";

		//Test of cashback
		BigDecimal cashback = new BigDecimal("0.10").multiply(valueSpended);

		//we are invoking service of spend
		consumerController.buy(EstablishmentTypeEnum.FOOD.value(), establishmentName, foodCardNumber, productDescription,valueSpended);

		//Recovering consumer to check operation
		consumer1 = repository.findById(consumer1.getId()).get();

		//operation was executed
		Assertions.assertThat(consumer1.getFoodCardBalance().setScale(2)).isEqualTo(foodCardBalance.subtract(valueSpended.subtract(cashback)).setScale(2));

		List<Extract> extracts = extractRepository.findLastExtractByConsumer(consumer1);
		Assertions.assertThat(extracts.size()).isGreaterThanOrEqualTo(1);

		Extract extract = extracts.get(0);

		Assertions.assertThat(extract.getValue().setScale(2)).isEqualTo(valueSpended.setScale(2));

	}

	@Test
	public void shouldReceivedErrorWhenTryToBuyWithFoodCardWith10PercentOfDiscount() {

		Consumer consumer1 = consumersSaved.get(0);
		Long foodCardNumber = consumer1.getFoodCardNumber();
		BigDecimal valueSpended = new BigDecimal("100");
		String establishmentName =  "Casa da Picanha";
		String productDescription = " 1k de Picanha";

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			//we are invoking service of spend
			consumerController.buy(EstablishmentTypeEnum.FOOD.value(), establishmentName, foodCardNumber, productDescription,valueSpended);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(InsufficientFundsFoodCardException.class)
				.hasMessageContaining("Insufficient Funds of Food Card");

	}

	@Test
	public void shouldBuyWithDrugstoreCard() {

		Consumer consumer3 = repository.findById(consumersSaved.get(2).getId()).get();

		Long drugstoreCardNumber = consumer3.getDrugstoreNumber();
		BigDecimal drugstoreCardBalance = consumer3.getDrugstoreCardBalance();
		BigDecimal valueSpended = BigDecimal.TEN;
		String establishmentName =  "Drogasil";
		String productDescription = "Dipirona";

		//we are invoking service of spend
		consumerController.buy(EstablishmentTypeEnum.DRUGSTORE.value(), establishmentName, drugstoreCardNumber, productDescription,valueSpended);

		//Recovering consumer to check operation
		consumer3 = repository.findById(consumer3.getId()).get();

		//operation was executed
		Assertions.assertThat(consumer3.getDrugstoreCardBalance().setScale(2)).isEqualTo(drugstoreCardBalance.subtract(valueSpended).setScale(2));

		List<Extract> extracts = extractRepository.findLastExtractByConsumer(consumer3);
		Assertions.assertThat(extracts.size()).isGreaterThanOrEqualTo(1);

		Extract extract = extracts.get(0);

		Assertions.assertThat(extract.getValue().setScale(2)).isEqualTo(valueSpended.setScale(2));

	}

	@Test
	public void shouldReceivedErrorWhenTryToBuyWithDrugstoreCard() {

		Consumer consumer1 = consumersSaved.get(2);
		Long drugstoreCardNumber = consumer1.getDrugstoreNumber();
		BigDecimal valueSpended = new BigDecimal("100");
		String establishmentName =  "Drogasil";
		String productDescription = "Dipirona";

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			//we are invoking service of spend
			consumerController.buy(EstablishmentTypeEnum.DRUGSTORE.value(), establishmentName, drugstoreCardNumber, productDescription,valueSpended);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(InsufficientFundsDrugstoreCardException.class)
				.hasMessageContaining("Insufficient Funds of Drugstore Card");

	}

	@Test
	public void shouldBuyWithFuelCardWithAddTax() {

		Consumer consumer2 = repository.findById(consumersSaved.get(1).getId()).get();
		Long fuelCardNumber = consumer2.getFuelCardNumber();
		BigDecimal fuelCardBalance = consumer2.getFuelCardBalance();
		BigDecimal valueSpended = new BigDecimal("50");
		String establishmentName =  "Posto Ipiranga";
		String productDescription = "Gasolina";

		//we are invoking service of spend
		consumerController.buy(EstablishmentTypeEnum.FUEL.value(), establishmentName, fuelCardNumber, productDescription,valueSpended);

		//Recovering consumer to check operation
		consumer2 = repository.findById(consumer2.getId()).get();

		//apply tax over the value of fuel
		BigDecimal tax = new BigDecimal("0.35").multiply(valueSpended);

		//operation was executed
		Assertions.assertThat(consumer2.getFuelCardBalance().setScale(2)).isEqualTo(fuelCardBalance.subtract(valueSpended.add(tax)).setScale(2));

		List<Extract> extracts = extractRepository.findLastExtractByConsumer(consumer2);
		Assertions.assertThat(extracts.size()).isGreaterThanOrEqualTo(1);

		Extract extract = extracts.get(0);

		Assertions.assertThat(extract.getValue().setScale(2)).isEqualTo(valueSpended.setScale(2));

	}

	@Test
	public void shouldReceivedErrorWhenTryToBuyWithFuelCard() {

		Consumer consumer2 = consumersSaved.get(1);
		Long fuelCardNumber = consumer2.getFuelCardNumber();
		BigDecimal valueSpended = new BigDecimal("150");
		String establishmentName =  "Posto Ipiranga";
		String productDescription = "Gasolina";

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			//we are invoking service of spend
			consumerController.buy(EstablishmentTypeEnum.FUEL.value(), establishmentName, fuelCardNumber, productDescription,valueSpended);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(InsufficientFundsFuelCardException.class)
				.hasMessageContaining("Insufficient Funds of Fuel Card");

	}

	@Test
	public void shouldReceivedErrorWhenTryToBuyWithEstablishmentTypeWrong() {

		Consumer consumer2 = consumersSaved.get(1);
		Long fuelCardNumber = consumer2.getFuelCardNumber();
		BigDecimal valueSpended = new BigDecimal("150");
		String establishmentName =  "Posto Ipiranga";
		String productDescription = "Gasolina";

		// when
		Throwable thrown = Assertions.catchThrowable(() -> {
			//we are invoking service of spend
			consumerController.buy(5, establishmentName, fuelCardNumber, productDescription,valueSpended);
		});

		// then
		Assertions.assertThat(thrown)
				.isInstanceOf(InvalidOperationCardException.class)
				.hasMessageContaining("Invalid Operation of Card");

	}

	public List<Consumer> getListOfConsumer(){

		return List.of(
				Consumer.builder()
						.name("Consumer 1")
						.documentNumber(00000000000L)
						.birthDate(LocalDate.of(1988, 01, 17))
						.mobilePhoneNumber(67991394961L)
						.residencePhoneNumber(6733000052L)
						.phoneNumber(6733000052L)
						.street("Rua Afonso Pena")
						.number("452L")
						.city("Cidade 1")
						.country("Brasil")
						.email("email1@gmail.com")
						.portalCode(79051720)
						.foodCardBalance(BigDecimal.TEN)
						.foodCardNumber(1111111111111111L)
						.fuelCardBalance(null)
						.fuelCardNumber(null)
						.drugstoreCardBalance(null)
						.drugstoreNumber(null)
						.build(),
				Consumer.builder()
						.name("Consumer 2")
						.documentNumber(11111111111L)
						.birthDate(LocalDate.of(1986, 02, 21))
						.mobilePhoneNumber(67991394962L)
						.residencePhoneNumber(6733000042L)
						.phoneNumber(6733000042L)
						.street("Rua Bahia")
						.number("544L")
						.city("Cidade 2")
						.country("Brasil")
						.email("email2@gmail.com")
						.portalCode(79051570)
						.foodCardBalance(null)
						.foodCardNumber(null)
						.fuelCardBalance(new BigDecimal("100"))
						.fuelCardNumber(2222222222222222L)
						.drugstoreCardBalance(null)
						.drugstoreNumber(null)
						.build(),
				Consumer.builder()
						.name("Consumer 3")
						.documentNumber(33333333333L)
						.birthDate(LocalDate.of(1985, 6, 23))
						.mobilePhoneNumber(67991394961L)
						.residencePhoneNumber(6733000052L)
						.phoneNumber(6733000052L)
						.street("Rua Afonso Pena")
						.number("452L")
						.city("Cidade 1")
						.country("Brasil")
						.email("email3@gmail.com")
						.portalCode(79051720)
						.foodCardBalance(null)
						.foodCardNumber(null)
						.fuelCardBalance(null)
						.fuelCardNumber(null)
						.drugstoreCardBalance(BigDecimal.TEN)
						.drugstoreNumber(3333333333333333L)
						.build());
	}

}
