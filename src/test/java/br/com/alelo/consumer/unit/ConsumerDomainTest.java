package br.com.alelo.consumer.unit;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.exception.RequiredFieldsException;
import org.junit.jupiter.api.Test;

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
}
