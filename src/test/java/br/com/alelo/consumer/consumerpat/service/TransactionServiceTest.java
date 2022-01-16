package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.ConsumerMock;
import br.com.alelo.consumer.consumerpat.model.Transaction;
import br.com.alelo.consumer.consumerpat.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ConsumerService consumerService;

    @Test
    void save_should_create_a_new_card_transaction() {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        Card card = consumer.getCards().get(0);
        String establishmentName = "Cocoa";
        String productDescription = "chocolate";
        BigDecimal value = BigDecimal.valueOf(50);
        Transaction transaction = transactionService.save(establishmentName, card.getNumber(), productDescription, value);
        assertNotNull(transaction);
        assertNotNull(transaction.getId());
        assertNotNull(transaction.getCardNumber());
        assertNotNull(transaction.getProductDescription());
        assertNotNull(transaction.getEstablishmentName());
        assertNotNull(transaction.getValue());
        assertNotNull(transaction.getPurchaseDateTime());
    }
}