package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.AddressORM;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerControllerTest extends IntegrationSuitTest {

    static final String CONSUMERS_API = "http://localhost:8080/consumer";

    @Test
    void createAConsumer() {
        var requestBody = new ConsumerORM();

        var response = http.postForEntity(CONSUMERS_API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void listCustomer() {
        var requestBody = new ConsumerORM();
        requestBody.setBirthDate(new Date());

        var response = http.postForEntity(CONSUMERS_API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
        var createdId = extractIdFromLocationHeader(response);

        var listResponse = http.getForEntity(CONSUMERS_API + "/consumerList", ConsumerORM[].class);
        assertTrue(listResponse.getStatusCode().is2xxSuccessful());
        assertNotNull(listResponse.getBody());

        var foundCreatedIdOnList = Arrays.stream(listResponse.getBody())
                .map(ConsumerORM::getId)
                .map(Long::toString)
                .anyMatch(id -> id.equals(createdId));
        assertTrue(foundCreatedIdOnList);
    }

    @Test
    void createAndEditACustomer() {
        var address = new AddressORM();
        address.setCity("Campinas");

        var requestBody = new ConsumerORM();
        requestBody.setBirthDate(new Date());
        requestBody.setAddress(address);

        var response = http.postForEntity(CONSUMERS_API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
        var createdId = extractIdFromLocationHeader(response);

        var updatedAddress = new AddressDTO();
        updatedAddress.setCity("Rio de Janeiro");

        var updated = new ConsumerDTO();
        updated.setAddress(updatedAddress);

        http.put(CONSUMERS_API + "/" + createdId, updated);

        var consumerResponse = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
        assertEquals(Integer.valueOf(createdId), consumerResponse.getBody().getId());
        assertEquals(updatedAddress.getCity(), consumerResponse.getBody().getAddress().getCity());
    }

}