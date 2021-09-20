package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.utils.MockUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConsumerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ConsumerRepository consumerRepository;

    @Test
    @Order(0)
    void createConsumer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/consumer")
        .content(MockUtil.getPathString("consumer/consumerSuccess.json"))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }


    @Test
    void createConsumerAlreadyExistEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/consumer")
                .content(MockUtil.getPathString("consumer/consumerSuccess.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$..message", Matchers.hasItem(
                        "Email already exists"
                ))).andReturn();
    }

    @Test
    void createConsumerAlreadyExistCard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/consumer")
                .content(MockUtil.getPathString("consumer/consumerExistsCard.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$..message", Matchers.hasItem(
                        "FOOD number already exists"
                ))).andReturn();
    }

    @Test
    @Order(1)
    void updateConsumer() throws Exception {
        consumerRepository.saveAndFlush(MockUtil.getConsumer());
        mockMvc.perform(MockMvcRequestBuilders.put(
                "/consumer")
                .content(MockUtil.getPathString("consumer/consumerUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        var consumer = consumerRepository.findById(1);
        Assertions.assertTrue(consumer.isPresent());
        Assertions.assertEquals("string@alelo.com.br", consumer.get().getEmail());
    }

    @Test
    void updateConsumerNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(
                "/consumer")
                .content(MockUtil.getPathString("consumer/consumerUpdateNotExist.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("Consumer not found")))
                .andReturn();

    }

    @Test
    void getConsumer() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get(
                "/consumer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
                .andReturn();

    }
}
