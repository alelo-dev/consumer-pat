package br.com.alelo.consumer.consumerpat.validation;


import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@SuppressWarnings("squid:S5778")
class ConsumerValidatorTest {

    @Autowired
    private ConsumerValidator consumerValidator;

    @Test
    void Get_consumer_by_optimal_when_its_empty() {
        assertThrows(ResponseStatusException.class, () -> consumerValidator.getConsumerUsingOptional(Optional.empty()));
    }

    @Test
    void Get_consumer_by_optimal_when_its_not_empty() {
        assertDoesNotThrow(() -> consumerValidator.getConsumerUsingOptional(Optional.of(Consumer.builder().build())));
    }

    @Test
    void Validate_consumer_when_its_null() {
        assertThrows(ResponseStatusException.class, () -> consumerValidator.validateConsumer(null));
    }

    @Test
    void Validate_consumer_when_id_is_null() {
        assertThrows(ResponseStatusException.class, () -> consumerValidator.validateConsumer(Consumer.builder().id(null).build()));
    }

    @Test
    void Validate_consumer_not_null() {
        assertDoesNotThrow(() -> consumerValidator.validateConsumer(Consumer.builder().id(1L).build()));
    }
}
