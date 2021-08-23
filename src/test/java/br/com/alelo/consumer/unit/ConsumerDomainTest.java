package br.com.alelo.consumer.unit;

import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.enumeration.CardType;
import br.com.alelo.consumerpat.core.exception.RequiredFieldsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumerDomainTest {

    @Test
    void validateConsumerRequiredFieldsTest() {
        ConsumerDomain consumerDomain = ConsumerDomain.builder()
                .build();

        assertThrows(RequiredFieldsException.class, consumerDomain::validateRequiredFields);

        try {
            consumerDomain.validateRequiredFields();
        } catch (RequiredFieldsException ex) {
            assertEquals(6, ex.getFieldsAndMessages().size());
        }
    }

    @Test
    void validateConsumerCodeTest() {
        ConsumerDomain consumerDomain = ConsumerDomain.builder()
                .build();

        consumerDomain.generateConsumerCode();
        assertEquals(36, consumerDomain.getConsumerCode().length());
        assertDoesNotThrow(() -> UUID.fromString(consumerDomain.getConsumerCode()));
    }

    @Test
    void validateGetAllCardsTest() {
        ConsumerDomain consumerDomain = ConsumerDomain.builder().build();

        assertNull(consumerDomain.getAllCardNumber());

        consumerDomain = ConsumerDomain.builder().cards(
                Set.of(CardDomain.builder()
                                .card("1234432165479854")
                                .balance(BigDecimal.valueOf(100L))
                                .type(CardType.FOOD)
                                .build()
                        ,
                        CardDomain.builder()
                                .card("1234432165479855")
                                .balance(BigDecimal.valueOf(100L))
                                .type(CardType.DRUGSTORE)
                                .build()
                )
        ).build();

        assertEquals(2, consumerDomain.getAllCardNumber().size());
        assertTrue(consumerDomain.getAllCardNumber().contains("1234432165479854"));
        assertTrue(consumerDomain.getAllCardNumber().contains("1234432165479855"));
    }
}
