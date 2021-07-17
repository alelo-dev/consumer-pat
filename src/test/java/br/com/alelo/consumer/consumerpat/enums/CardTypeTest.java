package br.com.alelo.consumer.consumerpat.enums;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.alelo.consumer.consumerpat.enums.CardType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardTypeTest {

    @Test
    void testFoodType_whenExpectedCode_ThenReturnSuccess() {
        assertEquals(1, FOOD.getCode());
    }

    @Test
    void testDrugstoreType_whenExpectedCode_ThenReturnSuccess() {
        assertEquals(2, DRUGSTORE.getCode());
    }

    @Test
    void testFuelType_whenExpectedCode_ThenReturnSuccess() {
        assertEquals(3, FUEL.getCode());
    }
}
