package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.request.AddBalanceRequest;
import br.com.alelo.consumer.consumerpat.model.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.FOOD;

@WebMvcTest(ConsumerController.class)
class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsumerService consumerService;

    @MockBean
    private CardService cardService;

    private Consumer consumer;

    @BeforeEach
    void setUp() {

        final Card card = Card.builder().type(FOOD).balance(100.0).number("123").build();
        consumer = Consumer.builder().id(1L).name("test").documentNumber("123456").cards(List.of(card)).build();
    }

    @Test
    public void testListAllConsumers() throws Exception {

        final List<Consumer> consumers = List.of(consumer);
        when(consumerService.findAll()).thenReturn(consumers);

        mockMvc.perform(get("/consumer/listAll")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(consumers)));
    }

    @Test
    public void testCreateConsumer() throws Exception {

        doNothing().when(consumerService).createConsumer(eq(consumer));

        mockMvc.perform(post("/consumer/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer))).andExpect(status().isOk());
    }

    @Test
    public void testFindConsumer() throws Exception {

        final Long consumerId = 1L;

        when(consumerService.findConsumerById(eq(consumerId))).thenReturn(consumer);

        mockMvc.perform(get("/consumer/findById/" + consumerId)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(consumer)));
    }

    @Test
    public void testUpdateConsumer() throws Exception {

        doNothing().when(consumerService).updateConsumer(eq(consumer));

        mockMvc.perform(put("/consumer/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer))).andExpect(status().isOk());
    }

    @Test
    public void testAddCardBalance() throws Exception {

        final AddBalanceRequest addBalanceRequest = new AddBalanceRequest("123", "123", 10);

        doNothing().when(cardService).addBalance(eq(addBalanceRequest));

        mockMvc.perform(put("/consumer/addCardBalance").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addBalanceRequest))).andExpect(status().isOk());
    }

    @Test
    public void testBuy() throws Exception {

        final Establishment establishment = Establishment.builder().name("test").type(FOOD).build();
        final BuyRequest buyRequest = new BuyRequest(establishment, "123", "123456", "product", new Date(), 100.0);

        doNothing().when(cardService).buySomething(eq(buyRequest));

        mockMvc.perform(post("/consumer/buy").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyRequest))).andExpect(status().isOk());
    }
}