package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.orm.CardORM;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import org.junit.jupiter.api.Test;

import static br.com.alelo.consumer.consumerpat.controller.ConsumerControllerTest.CONSUMERS_API;
import static org.junit.jupiter.api.Assertions.*;

class CardControllerTest extends IntegrationSuitTest {

    private static final String CARDS_API = "http://localhost:8080/cards";

    @Test
    void rechargeADrugstoreCard() {
        var card = new CardDTO();
        card.setNumber("22");
        card.setBalance(10);
        card.setType(CardType.DRUGSTORE);

        var requestBody = new ConsumerDTO();
        requestBody.setDrugstoreCard(card);

        var createdResponse = http.postForEntity(CONSUMERS_API, requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.postForEntity(CARDS_API + "/22/recharge", 100d, Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
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

        var createdResponse = http.postForEntity(CONSUMERS_API, requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.postForEntity(CARDS_API + "/33/recharge", 100d, Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
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

        var createdResponse = http.postForEntity(CONSUMERS_API, requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var updateResponse = http.postForEntity(CARDS_API + "/44/recharge", 100d, Void.class);
        assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
        assertTrue(updated.getStatusCode().is2xxSuccessful());
        assertNotNull(updated.getBody());
        assertEquals(112, updated.getBody().getFuelCard().getBalance());
    }

}