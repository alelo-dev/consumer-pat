package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.ConsumerMock;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ConsumerControllerTest {

    @Autowired
    private ConsumerController consumerController;

    @Autowired
    private ConsumerService consumerService;

    private static final String URI = "/consumer";

    @Test
    void findAll() throws Exception {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        List<Consumer> consumers = consumerController.findAll();
        assertNotNull(consumers);
        assertEquals(1, consumers.size());
    }

    @Test
    void create() throws Exception {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerController.create(consumer);
        assertNotNull(consumer);
        assertNotNull(consumer.getId());
    }

    @Test
    void update() {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        String expectedName = "tom";
        consumer.setName(expectedName);
        consumerService.update(consumer);
        assertEquals(expectedName, consumer.getName());
    }

}