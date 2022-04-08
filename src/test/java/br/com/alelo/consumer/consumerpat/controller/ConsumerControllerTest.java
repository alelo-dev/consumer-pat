package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.orm.AddressORM;
import br.com.alelo.consumer.consumerpat.entity.orm.CardORM;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerControllerTest extends IntegrationSuitTest {

    private static final String API = "http://localhost:8080/consumer";

    @Test
    void createAConsumer() {
        var requestBody = new ConsumerORM();

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void listCustomer() {
        var requestBody = new ConsumerORM();
        requestBody.setBirthDate(new Date());

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
        var createdId = extractIdFromLocationHeader(response);

        var listResponse = http.getForEntity(API + "/consumerList", ConsumerORM[].class);
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

        var response = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        assertEquals(201, response.getStatusCode().value());
        var createdId = extractIdFromLocationHeader(response);

        var updatedAddress = new AddressORM();

        var updated = new ConsumerORM();
        updated.setId(Integer.valueOf(createdId));
        updated.setAddress(updatedAddress);

        var updatedResponse = http.postForEntity(API + "/updateConsumer", updated, Void.class);
        assertTrue(updatedResponse.getStatusCode().is2xxSuccessful());

        var consumerResponse = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertEquals(Integer.valueOf(createdId), consumerResponse.getBody().getId());
        assertEquals(updatedAddress.getCity(), consumerResponse.getBody().getAddress().getCity());
    }

    @Test
    void rechargeADrugstoreCard() {
        var card = new CardORM();
        card.setNumber("22");
        card.setBalance(10);
        card.setType(CardType.DRUGSTORE);

        var requestBody = new ConsumerORM();
        requestBody.setDrugstoreCard(card);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.getForEntity(API + "/setcardbalance?cardNumber=22&value=100", Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(110, updated.getBody().getDrugstoreCard().getBalance());
    }

    @Test
    void rechargeAFoodCard() {
        var card = new CardORM();
        card.setNumber("33");
        card.setBalance(11);
        card.setType(CardType.FOOD);

        var requestBody = new ConsumerORM();
        requestBody.setFoodCard(card);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.getForEntity(API + "/setcardbalance?cardNumber=33&value=100", Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(111, updated.getBody().getFoodCard().getBalance());
    }

    @Test
    void rechargeAFuelCard() {
        var card = new CardORM();
        card.setNumber("44");
        card.setBalance(12);
        card.setType(CardType.FUEL);

        var requestBody = new ConsumerORM();
        requestBody.setFuelCard(card);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.getForEntity(API + "/setcardbalance?cardNumber=44&value=100", Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(112, updated.getBody().getFuelCard().getBalance());
    }

    @Test
    void orderFood() {
        var card = new CardORM();
        card.setNumber("124");
        card.setBalance(10);
        card.setType(CardType.FOOD);

        var requestBody = new ConsumerORM();
        requestBody.setFoodCard(card);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var orderResponse = http.getForEntity(API + "/buy?establishmentName=padaria&cardNumber=124&productDescription=coffee&value=1.35", Void.class);
        assertTrue(orderResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertEquals(8.785, updated.getBody().getFoodCard().getBalance(), 0.0);
    }

    @Test
    void orderDrugstore() {
        var card = new CardORM();
        card.setNumber("123");
        card.setBalance(10);
        card.setType(CardType.DRUGSTORE);

        var requestBody = new ConsumerORM();
        requestBody.setDrugstoreCard(card);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var orderResponse = http.getForEntity(API + "/buy?establishmentName=drogaria&cardNumber=123&productDescription=aspirin&value=1.35", Void.class);
        assertTrue(orderResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertEquals(8.65, updated.getBody().getDrugstoreCard().getBalance(), 0.0);
    }

    @Test
    void orderFuel() {
        var card = new CardORM();
        card.setNumber("456");
        card.setBalance(10);
        card.setType(CardType.FUEL);

        var requestBody = new ConsumerORM();
        requestBody.setFoodCard(card);

        var createdResponse = http.postForEntity(API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var orderResponse = http.getForEntity(API + "/buy?establishmentName=posto&cardNumber=456&productDescription=etanol&value=1.35", Void.class);
        assertTrue(orderResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(API + "/" + createdId, ConsumerORM.class);
        assertEquals(8.1775, updated.getBody().getFoodCard().getBalance(), 0.0);
    }
}