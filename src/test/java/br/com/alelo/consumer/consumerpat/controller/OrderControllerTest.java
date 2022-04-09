package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.OrderDTO;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.orm.CardORM;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import org.junit.jupiter.api.Test;

import static br.com.alelo.consumer.consumerpat.controller.ConsumerControllerTest.CONSUMERS_API;
import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest extends IntegrationSuitTest {

    static String ORDER_API = "http://localhost:8080/orders";

    @Test
    void orderFood() {
        var card = new CardORM();
        card.setNumber("124");
        card.setBalance(10);
        card.setType(CardType.FOOD);

        var requestBody = new ConsumerORM();
        requestBody.setFoodCard(card);

        var createdResponse = http.postForEntity(CONSUMERS_API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var orderDto = new OrderDTO();
        orderDto.setEstablishmentName("padaria");
        orderDto.setCardNumber("124");
        orderDto.setProductDescription("coffee");
        orderDto.setValue(1.35);

        var orderResponse = http.postForEntity(ORDER_API, orderDto, Void.class);
        assertTrue(orderResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
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

        var createdResponse = http.postForEntity(CONSUMERS_API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var orderDto = new OrderDTO();
        orderDto.setEstablishmentName("drogaria");
        orderDto.setCardNumber("123");
        orderDto.setProductDescription("aspirin");
        orderDto.setValue(1.35);

        var orderResponse = http.postForEntity(ORDER_API, orderDto, Void.class);
        assertTrue(orderResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
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

        var createdResponse = http.postForEntity(CONSUMERS_API + "/createConsumer", requestBody, Void.class);
        var createdId = extractIdFromLocationHeader(createdResponse);

        var orderDto = new OrderDTO();
        orderDto.setEstablishmentName("posto");
        orderDto.setCardNumber("456");
        orderDto.setProductDescription("etanol");
        orderDto.setValue(1.35);

        var orderResponse = http.postForEntity(ORDER_API, orderDto, Void.class);
        assertTrue(orderResponse.getStatusCode().is2xxSuccessful());

        var updated = http.getForEntity(CONSUMERS_API + "/" + createdId, ConsumerORM.class);
        assertEquals(8.1775, updated.getBody().getFoodCard().getBalance(), 0.0);
    }

}