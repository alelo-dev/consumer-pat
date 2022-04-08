package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerControllerTest extends IntegrationSuitTest {

    private static final String API = "http://localhost:8080/consumer";

    @Test
    void createAConsumer() {
        var requestBody = new Consumer();
        requestBody.setBirthDate(new Date());
        requestBody.setCity("Campinas");

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void createAndEditACustomer() {
        var requestBody = new Consumer();
        requestBody.setId(10);
        requestBody.setBirthDate(new Date());
        requestBody.setCity("Campinas");

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
        var createdId = extractIdFromLocationHeader(response);

        var listResponse = http.getForEntity(API + "/consumerList", Consumer[].class);
        assertTrue(listResponse.getStatusCode().is2xxSuccessful());
        assertNotNull(listResponse.getBody());

        var foundCreatedIdOnList = Arrays.stream(listResponse.getBody())
                .map(Consumer::getId)
                .map(Long::toString)
                .anyMatch(id -> id.equals(createdId));
        assertTrue(foundCreatedIdOnList);
    }
}