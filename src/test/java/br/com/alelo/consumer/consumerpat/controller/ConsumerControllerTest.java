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
    void listCustomer() {
        var requestBody = new Consumer();
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

    @Test
    void createAndEditACustomer() {
        var requestBody = new Consumer();
        requestBody.setBirthDate(new Date());
        requestBody.setCity("Campinas");

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
        var createdId = extractIdFromLocationHeader(response);

        var updated = new Consumer();
        updated.setId(Integer.valueOf(createdId));
        updated.setCity("Rio de Janeiro");

        var updatedResponse = http.postForEntity(API + "/updateConsumer", updated, Void.class);
        assertTrue(updatedResponse.getStatusCode().is2xxSuccessful());
    }

    @Test
    void rechargeADrugstoreCard() {
        var requestBody = new Consumer();
        requestBody.setDrugstoreNumber(22);
        requestBody.setDrugstoreCardBalance(10);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.getForEntity(API + "/setcardbalance?cardNumber=22&value=100", Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, Consumer.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(110, updated.getBody().getDrugstoreCardBalance());
    }

    @Test
    void rechargeAFoodCard() {
        var requestBody = new Consumer();
        requestBody.setFoodCardNumber(33);
        requestBody.setFoodCardBalance(11);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.getForEntity(API + "/setcardbalance?cardNumber=33&value=100", Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, Consumer.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(111, updated.getBody().getFoodCardBalance());
    }

    @Test
    void rechargeAFuelCard() {
        var requestBody = new Consumer();
        requestBody.setFuelCardNumber(44);
        requestBody.setFuelCardBalance(12);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.getForEntity(API + "/setcardbalance?cardNumber=44&value=100", Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, Consumer.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(112, updated.getBody().getFuelCardBalance());
    }
}