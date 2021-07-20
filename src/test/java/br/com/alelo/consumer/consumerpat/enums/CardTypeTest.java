package br.com.alelo.consumer.consumerpat.enums;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.alelo.consumer.consumerpat.enums.CardType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardTypeTest {

    @Test
    void Test_expected_food_type() {
        assertEquals(1, FOOD.getType());
    }

    @Test
    void Test_expected_drugstore_type() {
        assertEquals(2, DRUGSTORE.getType());
    }

    @Test
    void Test_expected_fuel_type() {
        assertEquals(3, FUEL.getType());
    }
}
