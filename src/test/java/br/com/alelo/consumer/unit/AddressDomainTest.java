package br.com.alelo.consumer.unit;

import br.com.alelo.consumer.consumerpat.core.domain.AddressDomain;
import br.com.alelo.consumer.consumerpat.core.exception.RequiredFieldsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressDomainTest {

    @Test
    void validateAddressRequiredFieldsTest() {
        AddressDomain addressDomain = AddressDomain.builder()
                .build();

        assertThrows(RequiredFieldsException.class, addressDomain::validateRequiredFields);

        try {
            addressDomain.validateRequiredFields();
        } catch (RequiredFieldsException ex) {
            assertEquals(5, ex.getFieldsAndMessages().size());
        }
    }
}
