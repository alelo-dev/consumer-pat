package br.com.alelo.consumer.consumerpat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.alelo.consumer.consumerpat.controller.CardController;
import br.com.alelo.consumer.consumerpat.controller.ConsumerController;

@AutoConfigureMockMvc
@SpringBootTest
class TestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ConsumerController consumerController;

	@Autowired
	private CardController cardController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(consumerController);
		Assertions.assertNotNull(cardController);
	}

	@Test
	void createConsumer200() throws Exception {
		String postValue = "{ \"address\": [ { \"city\": \"cascavel-PR\", \"country\": \"BR\", \"number\": 7845, \"portalCode\": 85812160, \"street\": \"voluntarios da patria\" } ], \"birthDate\": \"2022-10-02\", \"cards\": [ { \"balance\": 100, \"cardType\": \"DRUGSTORE\", \"expirationdate\": \"2022-10-02T03:06:06.549Z\", \"number\": \"987654321\" } ], \"documentNumber\": \"85236947\", \"email\": \"luiz.souza@gmail.com\", \"name\": \"luiz\", \"phones\": [ { \"ddd\": \"45\", \"number\": \"785362964\", \"phoneType\": \"MOBILE\" } ]}";

		mockMvc.perform(post("/consumer").contentType("application/json").content(postValue))
				.andExpect(status().isOk());
	}

	@Test
	void integrationNewConsumerSetBalanceAndBuy200() throws Exception {

		String postValue = "{ \"address\": [ { \"city\": \"cascavel-PR\", \"country\": \"BR\", \"number\": 8845, \"portalCode\": 85812160, \"street\": \"voluntarios da patria\" } ], \"birthDate\": \"2022-10-02\", \"cards\": [ { \"balance\": 100, \"cardType\": \"DRUGSTORE\", \"expirationdate\": \"2022-10-02T03:06:06.549Z\", \"number\": \"7456987654321\" } ], \"documentNumber\": \"985236947\", \"email\": \"luiz.souza@gmail.com\", \"name\": \"luiz\", \"phones\": [ { \"ddd\": \"45\", \"number\": \"98785362964\", \"phoneType\": \"MOBILE\" } ]}";

		mockMvc.perform(post("/consumer").contentType("application/json").content(postValue))
				.andExpect(status().isOk());

		mockMvc.perform(
				patch("/card/setcardbalance?cardNumber=7456987654321&value=150").contentType("application/json"))
				.andExpect(status().isOk());

		mockMvc.perform(post(
				"/card/buy?cardNumber=7456987654321&establishmentName=Estabelecimento%20A&establishmentType=2&productDescription=tapioca&value=23")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void createConsumerInvalidCardType400() throws Exception {
		String postValue = "{ \"address\": [ { \"city\": \"cascavel-PR\", \"country\": \"BR\", \"number\": 7845, \"portalCode\": 85812160, \"street\": \"voluntarios da patria\" } ], \"birthDate\": \"2022-10-02\", \"cards\": [ { \"balance\": 100, \"cardType\": \"STORE\", \"expirationdate\": \"2022-10-02T03:06:06.549Z\", \"number\": \"987654321\" } ], \"documentNumber\": \"85236947\", \"email\": \"luiz.souza@gmail.com\", \"name\": \"luiz\", \"phones\": [ { \"ddd\": \"45\", \"number\": \"785362964\", \"phoneType\": \"MOBILE\" } ]}";

		mockMvc.perform(post("/consumer").contentType("application/json").content(postValue))
				.andExpect(status().isBadRequest());
	}

	@Test
	void createConsumerInvalidCardNumber400() throws Exception {
		String postValue = "{ \"address\": [ { \"city\": \"cascavel-PR\", \"country\": \"BR\", \"number\": 7845, \"portalCode\": 85812160, \"street\": \"voluntarios da patria\" } ], \"birthDate\": \"2022-10-02\", \"cards\": [ { \"balance\": 100, \"cardType\": \"DRUGSTORE\", \"expirationdate\": \"2022-10-02T03:06:06.549Z\", \"number\": \"987654321\" } ], \"documentNumber\": \"6585236947\", \"email\": \"luiz.souza@gmail.com\", \"name\": \"luiz\", \"phones\": [ { \"ddd\": \"45\", \"number\": \"7885362964\", \"phoneType\": \"MOBILE\" } ]}";
		mockMvc.perform(post("/consumer").contentType("application/json").content(postValue))
				.andExpect(status().isBadRequest());
	}

	@Test
	void buyInvalidCardAndEstablishmentTypes400() throws Exception {

		mockMvc.perform(post(
				"/card/buy?cardNumber=987654321&establishmentName=Estabelecimento%20A&establishmentType=6&productDescription=tapioca&value=23")
						.contentType("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void buyCardNotFound400() throws Exception {

		mockMvc.perform(post(
				"/card/buy?cardNumber=987654321&establishmentName=Estabelecimento%20A&establishmentType=3&productDescription=tapioca&value=23")
						.contentType("application/json"))
				.andExpect(status().isBadRequest());
	}

}
