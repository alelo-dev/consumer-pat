package br.com.alelo.consumer.consumerpat.utils;

import br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture;
import org.junit.jupiter.api.Test;

import static br.com.alelo.consumer.consumerpat.utils.StringUtils.isEmpty;
import static br.com.alelo.consumer.consumerpat.utils.StringUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    private static final String STRING_VALUE = "foo, bar";

    @Test
    void isEmpty_WithNullValue_ShouldReturnTrue() {
        assertTrue(isEmpty(null));
    }

    @Test
    void isEmpty_WithEmptyValue_ShouldReturnTrue() {
        assertTrue(isEmpty(""));
    }

    @Test
    void isEmpty_WithWhiteSpacesValue_ShouldReturnTrue() {
        assertTrue(isEmpty("  "));
    }

    @Test
    void isEmpty_WithValidValue_ShouldReturnFalse() {
        assertFalse(isEmpty(STRING_VALUE));
    }

    @Test
    void isNotEmpty_WithValidValue_ShouldReturnTrue() {
        assertTrue(isNotEmpty(STRING_VALUE));
    }
}