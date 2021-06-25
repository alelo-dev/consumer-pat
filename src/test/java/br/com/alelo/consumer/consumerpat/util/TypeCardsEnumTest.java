package br.com.alelo.consumer.consumerpat.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeCardsEnumTest {

    @Test
    void getTaxByValue() {
        assertEquals(TypeCardsEnum.getTaxByValue(1), -0.1);
        assertEquals(TypeCardsEnum.getTaxByValue(2), 0);
        assertEquals(TypeCardsEnum.getTaxByValue(3), 0.35);
    }
}