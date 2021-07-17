package br.com.alelo.consumer.consumerpat.service.validator;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ConsumerValidatorTest {

    @Autowired
    private ConsumerValidator validator;

    @Test
    void validConsumer_WhenIsNull_ThenReturnResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.validConsumer(null));
    }

    @Test
    void validConsumer_WhenIdNull_ThenReturnResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.validConsumer(Consumer.builder().id(null).build()));
    }

    @Test
    void validConsumer_WhenNotNull_ThenReturnDoesNotException() {
        assertDoesNotThrow(() -> validator.validConsumer(Consumer.builder().id(1L).build()));
    }

    @Test
    void getConsumerByOptional_WhenIsEmpty_ThenReturnResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.getConsumerByOptional(Optional.empty()));
    }

    @Test
    void getConsumerByOptional_WhenIsNotEmpty_ThenReturnDoesNotException() {
        assertDoesNotThrow(() -> validator.getConsumerByOptional(Optional.of(Consumer.builder().build())));
    }

    @Test
    void validateBalanceForUpdate_WhenNotMatch_ThenReturnResponseStatusException() {
        final Consumer consumer = Consumer.builder()
                .cards(
                        Sets.newSet(Card.builder().balance(BigDecimal.ONE).build())
                )
                .build();

        final Consumer consumer2 = Consumer.builder()
                .cards(
                        Sets.newSet(Card.builder().balance(BigDecimal.TEN).build())
                )
                .build();
        assertThrows(ResponseStatusException.class, () -> validator.validateBalanceForUpdate(consumer, consumer2));
    }

    @Test
    void validateBalanceForUpdate_WhenMatch_ThenReturnDoesNotException() {
        final Consumer consumer = Consumer.builder()
                .cards(
                        Sets.newSet(Card.builder().balance(BigDecimal.ONE).build())
                )
                .build();

        final Consumer consumer2 = Consumer.builder()
                .cards(
                        Sets.newSet(Card.builder().balance(BigDecimal.ONE).build())
                )
                .build();
        assertDoesNotThrow(() -> validator.validateBalanceForUpdate(consumer, consumer2));
    }
}
