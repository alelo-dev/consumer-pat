package br.com.alelo.consumer.consumerpat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.DrugstoreCard;
import br.com.alelo.consumer.consumerpat.entity.FoodCard;
import br.com.alelo.consumer.consumerpat.entity.FuelCard;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentTypeEnum;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ConsumerTestApplicationTests {
	
	final String REQUEST_MAPPING = "/consumer/";
	
	@Autowired
	WebApplicationContext context;
	
	MockMvc mvc;
	
	ObjectMapper objectMapper;
	
	int cardNumber = 1;
	
	@BeforeAll
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void getConsumers() throws Exception {
		String url = this.REQUEST_MAPPING;
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}

	@Test
	public void createConsumer() throws Exception {
		createNewConsumer(cardNumber++, 100, cardNumber++, 100, cardNumber++, 100);
	}
	
	@Test
	public void updateConsumer() throws Exception {
		// Create a new consumer
		Consumer consumer = createNewConsumer(cardNumber++, 100, cardNumber++, 100, cardNumber++, 100);
		
		// Update the consumer
		String newConsumerName = "New Consumer Name";
		consumer.setName(newConsumerName);
		
		String url = this.REQUEST_MAPPING;
		this.mvc.perform(put(url).content(this.objectMapper.writeValueAsString(consumer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(newConsumerName)));
	}
	
	@Test
	public void updateCardBalance() throws Exception {
		// Create a new consumer
		int drugstoreCardNumber = cardNumber++;
		double drugstoreCardBalance = 100;
		int foodCardNumber = cardNumber++;
		double foodCardBalance = 100;
		int fuelCardNumber = cardNumber++;
		double fuelCardBalance = 100;
		Consumer consumer = createNewConsumer(drugstoreCardNumber, drugstoreCardBalance, foodCardNumber, foodCardBalance, fuelCardNumber, fuelCardBalance);
		
		// Update Drugstore Card Balance
		double newDrugstoreCardBalance = consumer.getDrugstoreCard().getBalance() + 100;
		this.mvc.perform(put(buildURLUpdateCardBalance(drugstoreCardNumber, newDrugstoreCardBalance)))
				.andExpect(status().isOk());
		
		// Update Food Card Balance
		double newFoodCardBalance = consumer.getFoodCard().getBalance() + 100;
		this.mvc.perform(put(buildURLUpdateCardBalance(foodCardNumber, newFoodCardBalance)))
				.andExpect(status().isOk());
		
		// Update Food Card Balance
		double newFuelCardBalance = consumer.getFuelCard().getBalance() + 100;
		this.mvc.perform(put(buildURLUpdateCardBalance(fuelCardNumber, newFuelCardBalance)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void makeAPurchase() throws Exception {
		// Create a new consumer
		int drugstoreCardNumber = cardNumber++;
		double drugstoreCardBalance = 100;
		int foodCardNumber = cardNumber++;
		double foodCardBalance = 100;
		int fuelCardNumber = cardNumber++;
		double fuelCardBalance = 100;
		createNewConsumer(drugstoreCardNumber, drugstoreCardBalance, foodCardNumber, foodCardBalance, fuelCardNumber, fuelCardBalance);
		
		// Buy (Food)
		makeNewPurchase(EstablishmentTypeEnum.FOOD, "Establishment Name", foodCardNumber, "Product Description", foodCardBalance)
				.andExpect(status().isCreated());
		
		// Buy (Drugstore)
		makeNewPurchase(EstablishmentTypeEnum.DRUGSTORE, "Establishment Name", drugstoreCardNumber, "Product Description", drugstoreCardBalance)
				.andExpect(status().isCreated());
		
		// Buy without balance (Drugstore)
		makeNewPurchase(EstablishmentTypeEnum.DRUGSTORE, "Establishment Name", drugstoreCardNumber, "Product Description", drugstoreCardBalance)
				.andExpect(status().isPreconditionFailed());
		
		// Buy with wrong card (Fuel)
		makeNewPurchase(EstablishmentTypeEnum.FUEL, "Establishment Name", drugstoreCardNumber, "Product Description", fuelCardBalance/2)
				.andExpect(status().isPreconditionFailed());
		
		// Buy (Fuel)
		makeNewPurchase(EstablishmentTypeEnum.FUEL, "Establishment Name", fuelCardNumber, "Product Description", fuelCardBalance/2)
				.andExpect(status().isCreated());
	}
	
	private String buildURLUpdateCardBalance(int cardNumber, double value) {
		StringBuilder url = new StringBuilder().append(this.REQUEST_MAPPING).append("cardbalance")
				.append("?").append("cardNumber=").append(cardNumber)
				.append("&").append("value=").append(value);
		return url.toString();
	}
	
	private ResultActions makeNewPurchase(EstablishmentTypeEnum establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws Exception {
		String url = this.REQUEST_MAPPING.concat("buy");
		
		return this.mvc.perform(post(url)
				.content(this.objectMapper.writeValueAsString(new ConsumerBuyDTO(establishmentType, establishmentName, cardNumber, productDescription, value)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
	}
	
	private Consumer createNewConsumer(int drugstoreCardNumber, double drugstoreCardBalance, int foodCardNumber, double foodCardBalance, int fuelCardNumber, double fuelCardBalance) throws Exception {
		Consumer consumer = buildTestConsumer(drugstoreCardNumber, drugstoreCardBalance, foodCardNumber, foodCardBalance, fuelCardNumber, fuelCardBalance);
		
		String url = this.REQUEST_MAPPING;
		MvcResult result = this.mvc.perform(post(url).content(this.objectMapper.writeValueAsString(consumer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		
		consumer = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Consumer>(){});
		
		return consumer;
	}
	
	private Consumer buildTestConsumer(int drugstoreCardNumber, double drugstoreCardBalance, int foodCardNumber, double foodCardBalance, int fuelCardNumber, double fuelCardBalance) {
		Consumer consumer = new Consumer();
		consumer.setBirthDate(new Date());
		consumer.setDocumentNumber(888888888);
		consumer.setName("Consumer Name");
		
		Address address = new Address();
		address.setCity("City");
		address.setCountry("Country");
		address.setPortalCode(333333333);
		address.setStreet("Street");
		address.setNumber(123);
		consumer.setAddress(address);
		
		Contact contact = new Contact();
		contact.setPhoneNumber(999999999);
		contact.setResidencePhoneNumber(999999999);
		contact.setEmail("email@test.com");
		contact.setMobilePhoneNumber(999999999);
		consumer.setContact(contact);
		
		consumer.setFoodCard(new FoodCard(foodCardNumber, foodCardBalance));
		consumer.setFuelCard(new FuelCard(fuelCardNumber, fuelCardBalance));
		consumer.setDrugstoreCard(new DrugstoreCard(drugstoreCardNumber, drugstoreCardBalance));
		
		return consumer;
	}
}
