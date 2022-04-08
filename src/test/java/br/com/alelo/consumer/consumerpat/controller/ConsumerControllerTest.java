package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestOperations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ConsumerControllerTest {

    private static final String API = "http://localhost:8080/consumer";

    @Autowired
    RestOperations http;

    @Test
    void createAConsumer() {
        var requestBody = new Consumer();
        requestBody.setBirthDate(new Date());
        requestBody.setCity("Campinas");

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
    }

}