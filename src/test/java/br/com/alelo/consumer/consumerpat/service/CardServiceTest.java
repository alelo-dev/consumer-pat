package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.CardType;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.ConsumerMock;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private ConsumerService consumerService;

    private Consumer consumer;

    @BeforeEach
    void init() {
        consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
    }

    @Test
    void increaseFunds_should_increase_a_card_funds() {
        Card card = consumer.getCards().get(0);
        BigDecimal expectedFunds = BigDecimal.valueOf(1200).setScale(2);
        Card actualCard = cardService.increaseFunds(card.getNumber(), BigDecimal.valueOf(200));
        assertEquals(expectedFunds, actualCard.getFunds().setScale(2));
    }

    @Test
    void increaseFunds_should_return_exception_when_funds_value_is_zero() {
        Card card = consumer.getCards().get(0);
        assertThrows(ResponseStatusException.class, () -> cardService.increaseFunds(card.getNumber(), BigDecimal.ZERO));
    }

    @Test
    void increaseFunds_should_return_exception_when_funds_value_is_less_than_zero() {
        Card card = consumer.getCards().get(0);
        assertThrows(ResponseStatusException.class, () -> cardService.increaseFunds(card.getNumber(), BigDecimal.valueOf(-50)));
    }

    @Test
    void increaseFunds_should_return_exception_when_card_number_is_not_found() {
        assertThrows(ResponseStatusException.class, () -> cardService.increaseFunds(BigInteger.valueOf(555L), BigDecimal.valueOf(-50)));
    }

    @Test
    void charge_should_return_exception_when_card_number_is_not_found() {
           assertThrows(ResponseStatusException.class, () -> cardService.charge(BigInteger.valueOf(55L), BigDecimal.ZERO));
    }

    @Test
    void charge_should_return_exception_when_card_has_insufficient_funds() {
        Card card = consumer.getCards().get(0);
        assertThrows(ResponseStatusException.class, () -> cardService.charge(card.getNumber(), BigDecimal.valueOf(1200)));
    }

    @Test
    void charge_should_subtract_funds_from_drugstore_card() {
        Card card = consumer.getCards().stream().filter(e -> CardType.Drugstore.equals(e.getType())).findFirst().get();
        BigDecimal expectedFunds = card.getFunds().subtract(BigDecimal.valueOf(50)).setScale(2);
        Card actualCard = cardService.charge(card.getNumber(), BigDecimal.valueOf(50).setScale(2));
        assertEquals(expectedFunds, actualCard.getFunds());
    }

    @Test
    void charge_should_subtract_funds_from_food_card() {
        Card card = consumer.getCards().stream().filter(e -> CardType.Food.equals(e.getType())).findFirst().get();
        BigDecimal expectedFunds = BigDecimal.valueOf(910).setScale(2);
        Card actualCard = cardService.charge(card.getNumber(), BigDecimal.valueOf(100));
        assertEquals(expectedFunds, actualCard.getFunds().setScale(2));
    }

    @Test
    void charge_should_subtract_funds_from_fuel_card() {
        Card card = consumer.getCards().stream().filter(e -> CardType.Fuel.equals(e.getType())).findFirst().get();
        BigDecimal expectedFunds = BigDecimal.valueOf(865).setScale(2);
        Card actualCard = cardService.charge(card.getNumber(), BigDecimal.valueOf(100));
        assertEquals(expectedFunds, actualCard.getFunds().setScale(2));
    }
}