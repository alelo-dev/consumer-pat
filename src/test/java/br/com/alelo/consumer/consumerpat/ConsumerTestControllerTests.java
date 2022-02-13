package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumerTestControllerTests {

	static class ConsumerControllerTestConfig{

		@Bean
		public ConsumerController beanConsumerController(){
			return new ConsumerController();
		}
	}

	@Autowired
	ConsumerController controller;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName("/consumer/create -> JSON Validation Name")
	public void jsonConsumerNameValidationError(){
		Assertions.assertThrows(ValidationException.class, () -> new ConsumerRequest("Nome inv@lido", "email@alelo.com.br"), "Nome inválido");
	}

	@Test
	@DisplayName("/consumer/create -> JSON Validation E-mail")
	public void jsonConsumerEmailValidationError(){
		Assertions.assertThrows(ValidationException.class, () -> new ConsumerRequest("Nome valido", "email@alel"), "E-mail inválido");
	}

	@Test
	@DisplayName("/consumer/create -> JSON Validation ID on save new consumer")
	public void saveBadRequestId() throws Exception {
		ConsumerRequest request =  new ConsumerRequest("Nome Valido", "email@alelo.com.br");
		request.setDocumentNumber("123456789");
		request.setId(2);

		this.mockMvc
				.perform(post("/consumer/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Campo ID não permitido para inserir")));
	}

	@Test
	@DisplayName("/consumer/create -> Creating OK")
	public void saveOk() throws Exception {
		ConsumerRequest request =  new ConsumerRequest("Nome Valido", "email@alelo.com.br");
		request.setDocumentNumber("123456789");
		request.setId(null);

		this.mockMvc
				.perform(post("/consumer/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated());
	}

}
