package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsumerControllerTest {

	private final String BASE_URL = "/consumer";
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ConsumerRepository repository;

	private List<Consumer> consumersSaved;

	@BeforeAll
	public void setUp() {
		objectMapper = new ObjectMapper();
		consumersSaved = getListOfConsumer();
		repository.saveAll(consumersSaved);
	}

	@Test
	public void shouldListConsumers() throws Exception {

		List<Consumer> consumers = repository.findAll();

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/consumerList")
				.accept(MediaType.APPLICATION_JSON))
      			.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(consumers.size()));
	}

	@Test
	public void shouldCreateConsumer() throws Exception {

		Consumer consumer = new Consumer("Wallace Nascimento", 2016615192L);

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createConsumer")
						.content(objectMapper.writeValueAsString(consumer))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldReceiveErrorWhenTryToCreateConsumerDuplicate() throws Exception {

		Consumer consumer = consumersSaved.get(0);
		Consumer consumer2 = new Consumer("Wallace Nascimento", consumer.getDocumentNumber());

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createConsumer")
						.content(objectMapper.writeValueAsString(consumer2))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldReceiveErrorWhenTryToCreateConsumerWithCardDuplicate() throws Exception {

		Consumer consumer = consumersSaved.get(0);
		Consumer consumer2 = new Consumer("Wallace Nascimento", consumer.getDocumentNumber(), consumer.getFoodCardNumber(), null, null);

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createConsumer")
						.content(objectMapper.writeValueAsString(consumer2))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldUpdateConsumer() throws Exception {

		Consumer consumer = consumersSaved.get(0);
		consumer.setName("Novo Nome");

		mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/updateConsumer")
						.content(objectMapper.writeValueAsString(consumer))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNoContent());

	}

	@Test
	public void shouldReceiveErrorWhenUpdateConsumerNotFound() throws Exception {

		Consumer consumer = new Consumer("Joao da Silva", 54878L);
		consumer.setId(500L);

		mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/updateConsumer")
						.content(objectMapper.writeValueAsString(consumer))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldSetBalanceConsumer() throws Exception {

		Consumer consumer = consumersSaved.get(0);

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/setcardbalance")
						.param("cardNumber", String.valueOf(consumer.getFoodCardNumber()))
						.param("value", new BigDecimal("5").toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNoContent());

	}

	@Test
	public void shouldReceiveErrorWhenSetBalanceConsumerWithInvalidCard() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/setcardbalance")
						.param("cardNumber", String.valueOf(2016615197))
						.param("value", new BigDecimal("5").toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());

	}


	@Test
	public void shouldBuySomeThingWithCard() throws Exception {

		Consumer consumer = consumersSaved.get(0);

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/buy")
						.param("establishmentType", String.valueOf(EstablishmentTypeEnum.FOOD.value()))
						.param("establishmentName", "Ifood")
						.param("cardNumber", String.valueOf(consumer.getFoodCardNumber()))
						.param("productDescription", "Cookie")
						.param("value", new BigDecimal("5").toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNoContent());

	}

	@Test
	public void shouldReceiveErrorWhenBuySomeThingWithInvalidCard() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/buy")
						.param("establishmentType", String.valueOf(EstablishmentTypeEnum.FOOD.value()))
						.param("establishmentName", "Ifood")
						.param("cardNumber", String.valueOf(2016615197))
						.param("productDescription", "Cookie")
						.param("value", new BigDecimal("5").toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());

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
