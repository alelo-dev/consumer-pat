package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ConsumerTestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConsumerController consumerController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(consumerController);
    }


    @Test
    void teste_create() throws Exception {

        String postValue = "{\"name\":\"Nil Obermuller\",\"documentNumber\":\"12808560\",\"birthDate\":\"1982-05-24\",\"email\":\"nil.obermuller@gmail.com\",\"phone\":{\"mobile\":10,\"residence\":30,\"phoneNumber\":20},\"address\":{\"street\":\"Dezesseis\",\"number\":424,\"city\":\"Rio de Janeiro\",\"country\":\"Brazil\",\"portalCode\":20720},\"cards\":[{\"number\":\"1234432178941234\",\"balance\":0,\"cardType\":\"FOOD_CARD\"},{\"number\":\"0000325245674321\",\"balance\":0,\"cardType\":\"DRUGSTORE_CARD\"},{\"number\":\"0000325245679876\",\"balance\":0,\"cardType\":\"FUEL_CARD\"}]}";

        mockMvc.perform(post("/consumers")
                .contentType("application/json")
                .content(postValue)
        ).andExpect(status().isCreated());
    }

}
