package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import utils.types.CardAndEstablishmentType;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void listAllConsumers() throws Exception {
        final Card card = Card.builder().cardType(CardAndEstablishmentType.FOOD).balance(100.0).number("123").build();
        final Consumer consumer =
                Consumer.builder().id(1L).name("test").documentNumber("123456").cards(List.of(card)).build();

        final List<Consumer> consumers = List.of(consumer);
        given(consumerService.findAll()).willReturn(consumers);

        mockMvc.perform(get("/consumer/listAll")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(consumers)));
    }

    @Test
    public void createConsumer() throws Exception {
        final Card card = Card.builder().cardType(CardAndEstablishmentType.FOOD).balance(100.0).number("123").build();
        final Consumer consumer =
                Consumer.builder().name("test").documentNumber("123456").cards(List.of(card)).build();

        doNothing().when(consumerService).createConsumer(eq(consumer));

        mockMvc.perform(post("/consumer/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer))).andExpect(status().isOk());
    }

    @Test
    void findConsumer() throws Exception {
        final Long consumerId = 1L;
        final Card card = Card.builder().cardType(CardAndEstablishmentType.FOOD).balance(100.0).number("123").build();
        final Consumer consumer =
                Consumer.builder().id(consumerId).name("test").documentNumber("123456").cards(List.of(card)).build();

        given(consumerService.findConsumerById(eq(consumerId))).willReturn(consumer);

        mockMvc.perform(get("/consumer/findById/" + consumerId)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(consumer)));
    }

    @Test
    void updateConsumer() {
    }

    @Test
    void addCardBalance() {
    }

    @Test
    void buy() {
    }
}