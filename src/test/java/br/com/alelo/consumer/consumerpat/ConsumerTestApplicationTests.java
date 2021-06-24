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
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.util.Constants;

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
		double newDrugstoreCardBalance = consumer.getDrugstoreCardBalance() + 100;
		this.mvc.perform(put(buildURLUpdateCardBalance(drugstoreCardNumber, newDrugstoreCardBalance)))
				.andExpect(status().isOk());
		
		// Update Food Card Balance
		double newFoodCardBalance = consumer.getFoodCardBalance() + 100;
		this.mvc.perform(put(buildURLUpdateCardBalance(foodCardNumber, newFoodCardBalance)))
				.andExpect(status().isOk());
		
		// Update Food Card Balance
		double newFuelCardBalance = consumer.getFuelCardBalance() + 100;
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
		makeNewPurchase(Constants.ESTABLISHMENT_TYPE_FOOD, "Establishment Name", foodCardNumber, "Product Description", foodCardBalance)
				.andExpect(status().isCreated());
		
		// Buy (Drugstore)
		makeNewPurchase(Constants.ESTABLISHMENT_TYPE_DRUGSTORE, "Establishment Name", drugstoreCardNumber, "Product Description", drugstoreCardBalance)
				.andExpect(status().isCreated());
		
		// Buy without balance (Drugstore)
		makeNewPurchase(Constants.ESTABLISHMENT_TYPE_DRUGSTORE, "Establishment Name", drugstoreCardNumber, "Product Description", drugstoreCardBalance)
				.andExpect(status().isPreconditionFailed());
		
		// Buy with wrong card (Fuel)
		makeNewPurchase(Constants.ESTABLISHMENT_TYPE_FUEL, "Establishment Name", drugstoreCardNumber, "Product Description", fuelCardBalance)
				.andExpect(status().isPreconditionFailed());
		
		// Buy (Fuel)
		makeNewPurchase(Constants.ESTABLISHMENT_TYPE_FUEL, "Establishment Name", fuelCardNumber, "Product Description", fuelCardBalance/2)
				.andExpect(status().isCreated());
	}
	
	private String buildURLUpdateCardBalance(int cardNumber, double value) {
		StringBuilder url = new StringBuilder().append(this.REQUEST_MAPPING).append("cardbalance")
				.append("?").append("cardNumber=").append(cardNumber)
				.append("&").append("value=").append(value);
		return url.toString();
	}
	
	private ResultActions makeNewPurchase(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws Exception {
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
		consumer.setCity("City");
		consumer.setCountry("Country");
		consumer.setDocumentNumber(888888888);
		consumer.setDrugstoreCardBalance(drugstoreCardBalance);
		consumer.setDrugstoreNumber(drugstoreCardNumber);
		consumer.setEmail("email@test.com");
		consumer.setFoodCardBalance(foodCardBalance);
		consumer.setFoodCardNumber(foodCardNumber);
		consumer.setFuelCardBalance(fuelCardBalance);
		consumer.setFuelCardNumber(fuelCardNumber);
		consumer.setMobilePhoneNumber(999999999);
		consumer.setName("Consumer Name");
		consumer.setNumber(123);
		consumer.setPhoneNumber(999999999);
		consumer.setPortalCode(333333333);
		consumer.setResidencePhoneNumber(999999999);
		consumer.setStreet("Street");
		
		return consumer;
	}
}
