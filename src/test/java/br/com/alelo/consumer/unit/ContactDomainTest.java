package br.com.alelo.consumer.unit;

import br.com.alelo.consumerpat.core.domain.ContactDomain;
import br.com.alelo.consumerpat.core.exception.RequiredFieldsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactDomainTest {

    @Test
    void validateContactRequiredFieldsTest() {
        ContactDomain contactDomain = ContactDomain.builder()
                .build();

        assertThrows(RequiredFieldsException.class, contactDomain::validateRequiredFields);

        try {
            contactDomain.validateRequiredFields();
        } catch (RequiredFieldsException ex) {
            assertEquals(3, ex.getFieldsAndMessages().size());
        }
    }

}
