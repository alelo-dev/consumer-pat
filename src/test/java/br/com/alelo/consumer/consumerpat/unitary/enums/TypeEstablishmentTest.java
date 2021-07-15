package br.com.alelo.consumer.consumerpat.unitary.enums;

import br.com.alelo.consumer.consumerpat.enums.TypeEstablishment;
import br.com.alelo.consumer.consumerpat.exception.UnknownEnumValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypeEstablishmentTest {

    @Test
    public void testValidEnumMethodOf() {
        assertEquals(TypeEstablishment.FOOD, TypeEstablishment.of(1));
    }

    @Test
    public void testNotValidEnumMethodOf() {
        assertThrows(UnknownEnumValueException.class, () -> TypeEstablishment.of(99));
    }
}