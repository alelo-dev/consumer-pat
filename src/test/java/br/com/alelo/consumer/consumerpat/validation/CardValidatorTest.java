package br.com.alelo.consumer.consumerpat.validation;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.enums.CardType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static java.math.RoundingMode.HALF_UP;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("squid:S5778")
class CardValidatorTest {

    @Autowired
    private CardValidator cardValidator;

    @MockBean
    private CardRepository cardRepository;

    private Card cardFood;
    private Card cardDrugstore;
    private Card cardFuel;

    @BeforeEach
    public void setup() {
        cardFood = Card.builder()
                .id(1L)
                .cardType(FOOD)
                .balance(new BigDecimal(100))
                .build();
        cardDrugstore = Card.builder()
                .id(2L)
                .cardType(DRUGSTORE)
                .balance(new BigDecimal(110))
                .build();
        cardFuel = Card.builder()
                .id(3L)
                .cardType(CardType.FUEL)
                .balance(new BigDecimal(120))
                .build();

    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_drugstore_value_exceeds_the_balance() {
        assertThrows(ResponseStatusException.class, () -> cardValidator.getNewBalance(cardDrugstore,
                DRUGSTORE, BigDecimal.valueOf(965)));
    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_food_value_exceeds_the_balance() {
        assertThrows(ResponseStatusException.class, () -> cardValidator.getNewBalance(cardFood,
                FOOD, BigDecimal.valueOf(6378)));
    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_fuel_value_exceeds_the_balance() {
        assertThrows(ResponseStatusException.class, () -> cardValidator.getNewBalance(cardFuel,
                FUEL, BigDecimal.valueOf(878)));
    }

    @Test
    void Validate_card_when_value_is_null() {
        assertThrows(ResponseStatusException.class, () -> cardValidator.validateCard(1L, null));
    }

    @Test
    void Validate_card_empty() {
        assertThrows(ResponseStatusException.class, () -> cardValidator.validateCard(1L, BigDecimal.ONE));
    }

    @Test
    void Validate_card_when_value_is_not_null_and_card_is_not_empty() {
        when(cardRepository.findByNumber(anyLong())).thenReturn(Optional.of(cardFood));

        final Card card = cardValidator.validateCard(1L, BigDecimal.ONE);
        assertNotNull(card);
    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_is_different() {
        assertThrows(ResponseStatusException.class, () ->
                cardValidator.getNewBalance(cardFood, DRUGSTORE, BigDecimal.ONE));
        assertThrows(ResponseStatusException.class, () ->
                cardValidator.getNewBalance(cardDrugstore, FOOD, BigDecimal.ONE));
        assertThrows(ResponseStatusException.class, () ->
                cardValidator.getNewBalance(cardFuel, FOOD, BigDecimal.ONE));
    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_is_drugstore() {
        final BigDecimal newBalance = cardValidator.getNewBalance(cardDrugstore, DRUGSTORE, BigDecimal.ONE);
        assertEquals(cardDrugstore.getBalance().subtract(BigDecimal.ONE).setScale(2, HALF_UP), newBalance);
    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_is_food() {
        final BigDecimal valueExpected = cardFood.getBalance().subtract(BigDecimal.TEN.subtract(
                BigDecimal.TEN.multiply(BigDecimal.valueOf(0.1)))).setScale(2, HALF_UP);

        final BigDecimal newBalance = cardValidator.getNewBalance(cardFood, FOOD, BigDecimal.TEN);
        assertEquals(valueExpected, newBalance);
    }

    @Test
    void Get_addition_for_balance_by_type_when_the_type_is_fuel() {
        final BigDecimal valueExpected = cardFuel.getBalance().subtract(BigDecimal.TEN.add(
                BigDecimal.TEN.multiply(BigDecimal.valueOf(0.35)))).setScale(2, HALF_UP);

        final BigDecimal newBalance = cardValidator.getNewBalance(cardFuel, FUEL, BigDecimal.TEN);
        assertEquals(valueExpected, newBalance);
    }
}
