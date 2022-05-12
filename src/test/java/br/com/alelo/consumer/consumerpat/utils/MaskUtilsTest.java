package br.com.alelo.consumer.consumerpat.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaskUtilsTest {

    @Test
    public void testRemoveDocumentNumberMask() {

        final String expected = "11111111111";

        final String response = MaskUtils.removeDocumentNumberMask("111.111.111-11");

        assertEquals(expected, response);
    }

    @Test
    public void removeCardNumberMask() {

        final String expected = "1111111111111111";

        final String response = MaskUtils.removeDocumentNumberMask("1111.1111.1111.1111");

        assertEquals(expected, response);
    }
}