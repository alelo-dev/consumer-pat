package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.CardType;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.ConsumerMock;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardControllerTest {

    @Autowired
    private CardController cardController;

    @Autowired
    private ConsumerService consumerService;

    private Consumer consumer;

    @BeforeEach
    void init() {
        consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
    }

    @Test
    void increaseBalance() {
        Card card = consumer.getCards().get(0);
        BigDecimal expectedFunds = BigDecimal.valueOf(1200).setScale(2);
        Card actualCard = cardController.increaseFunds(card.getNumber(), BigDecimal.valueOf(200));
        assertEquals(expectedFunds, actualCard.getFunds().setScale(2));
    }

    @Test
    void charge() {
        Card card = consumer.getCards().stream().filter(e -> CardType.Drugstore.equals(e.getType())).findFirst().get();
        BigDecimal expectedFunds = card.getFunds().subtract(BigDecimal.valueOf(50)).setScale(2);
        Card actualCard = cardController.charge(card.getNumber(), BigDecimal.valueOf(50).setScale(2), "Kokoa", "chocolate");
        assertEquals(expectedFunds, actualCard.getFunds());
    }
}