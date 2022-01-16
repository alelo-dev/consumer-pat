package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.*;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    @Test
    void findAll_should_return_all_existing_consumers() {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        List<Consumer> consumers = consumerService.findAll();
        assertNotNull(consumers);
        assertEquals(1, consumers.size());
    }

    @Test
    void create_should_create_a_new_consumer() {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        assertNotNull(consumer);
        assertNotNull(consumer.getId());
        assertNotNull(consumer.getContact());
        assertNotNull(consumer.getCards());
        assertEquals(3, consumer.getCards().size());
        assertNotNull(consumer.getBirthDate());
        assertNotNull(consumer.getName());
        assertNotNull(consumer.getDocumentNumber());
    }

    @Test
    void create_should_create_consumer_contact() {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        assertNotNull(consumer);
        assertNotNull(consumer.getId());
        Contact contact = consumer.getContact();
        assertNotNull(contact);
        assertNotNull(contact.getId());
        assertNotNull(contact.getCity());
        assertNotNull(contact.getCountry());
        assertNotNull(contact.getEmail());
        assertNotNull(contact.getMobileNumber());
        assertNotNull(contact.getPostalCode());
        assertNotNull(contact.getStreet());
        assertNotNull(contact.getTelephoneNumber());
    }

    @Test
    void update_should_update_consumer_data() {
        Consumer consumer = ConsumerMock.getConsumer();
        consumerService.create(consumer);
        String expectedName = "tom";
        consumer.setName(expectedName);
        consumerService.update(consumer);
        assertEquals(expectedName, consumer.getName());
    }


}