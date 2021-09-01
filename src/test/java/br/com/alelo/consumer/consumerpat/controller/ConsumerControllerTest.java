package br.com.alelo.consumer.consumerpat.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ConsumerControllerTest {

	private MockMvc mockMvc;
	
	@Test
	public void testListAllConsumers() throws Exception {
        
		// TODO...
		mockMvc.perform(MockMvcRequestBuilders.get("consumer/consumerList")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print());
	}

	@Test
	public void testCreateConsumer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateConsumer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuy() {
		fail("Not yet implemented");
	}

}
