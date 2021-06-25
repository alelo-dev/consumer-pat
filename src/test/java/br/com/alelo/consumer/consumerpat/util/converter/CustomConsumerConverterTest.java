package br.com.alelo.consumer.consumerpat.util.converter;

import br.com.alelo.consumer.consumerpat.mocks.MockConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomConsumerConverterTest {

    MockConsumer inputObject;

    @BeforeEach
    void setUp() {
        inputObject = new MockConsumer();
    }

    @Test
    void parseObject() {
        var vo = CustomConsumerConverter.parseObject(inputObject.mockEntity());
        assertEquals(vo.getId(), 0);
        assertEquals(vo.getName(), "Name Test0");
        assertEquals(vo.getDocumentNumber(), "0");
        assertNotNull(vo.getBirthDate());
        assertEquals(vo.getMobilePhoneNumber(), 0);
        assertEquals(vo.getResidencePhoneNumber(), 0);
        assertEquals(vo.getPhoneNumber(), 0);
        assertEquals(vo.getEmail(), "Email Test0");
        assertEquals(vo.getStreet(), "Street Test0");
        assertEquals(vo.getNumber(), 0);
        assertEquals(vo.getCity(), "City Test0");
        assertEquals(vo.getCountry(), "Country Test0");
        assertEquals(vo.getPostalCode(), "0");
        assertEquals(vo.getFoodCardNumber(), 1111111111111111L);
        assertEquals(vo.getFoodCardBalance(), new BigDecimal(100));
        assertEquals(vo.getFuelCardNumber(), 3333333333333333L);
        assertEquals(vo.getFuelCardBalance(), new BigDecimal(100));
        assertEquals(vo.getDrugstoreCardNumber(), 2222222222222222L);
        assertEquals(vo.getDrugstoreCardBalance(), new BigDecimal(100));
    }

    @Test
    void parseList() {
    }
}