package br.com.alelo.consumer.consumerpat.service.validator;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.repository.ICardRepository;
import br.com.alelo.consumer.consumerpat.util.BigDecimalUtil;
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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CardValidatorTest {

    @Autowired
    private CardValidator validator;

    @MockBean
    private ICardRepository repository;

    @Autowired
    private BigDecimalUtil util;

    private Card cardFood;
    private Card cardFuel;
    private Card cardDrugstore;

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
    void validCard_WhenValueIsNull_ThenReturnResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.validCard(1L, null));
    }

    @Test
    void validCard_WhenValueNotNullButCardEmpty_ThenReturnResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.validCard(1L, BigDecimal.ONE));
    }

    @Test
    void validCard_WhenValueNotNullAndCardNotEmpty_ThenReturnDoesNotException() {
        when(repository.findByNumber(anyLong())).thenReturn(Optional.of(cardFood));

       final Card card = validator.validCard(1L, BigDecimal.ONE);
        assertNotNull(card);
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeIsDifferent_ThenResponseStatusException() {
        assertThrows(ResponseStatusException.class, () ->
                validator.getAdditionForBalanceByType(cardFood, DRUGSTORE, BigDecimal.ONE));
        assertThrows(ResponseStatusException.class, () ->
                validator.getAdditionForBalanceByType(cardDrugstore, FOOD, BigDecimal.ONE));
        assertThrows(ResponseStatusException.class, () ->
                validator.getAdditionForBalanceByType(cardFuel, FOOD, BigDecimal.ONE));
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeIsDrugstore_ThenDoesNotException() {
        final BigDecimal newBalance = validator.getAdditionForBalanceByType(cardDrugstore, DRUGSTORE, BigDecimal.ONE);
        assertEquals(util.getValueWithScale(cardDrugstore.getBalance().subtract(BigDecimal.ONE)), newBalance);
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeIsFood_ThenDoesNotException() {
        final BigDecimal valueExpected = util.getValueWithScale(
                cardFood.getBalance().subtract(
                    BigDecimal.TEN.subtract(
                        BigDecimal.TEN.multiply(BigDecimal.valueOf(0.1))
                    ))
            );
        final BigDecimal newBalance = validator.getAdditionForBalanceByType(cardFood, FOOD, BigDecimal.TEN);
        assertEquals(valueExpected, newBalance);
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeIsFuel_ThenDoesNotException() {
        final BigDecimal valueExpected = util.getValueWithScale(
                cardFuel.getBalance().subtract(
                        BigDecimal.TEN.add(
                                BigDecimal.TEN.multiply(BigDecimal.valueOf(0.35))
                        ))
        );
        final BigDecimal newBalance = validator.getAdditionForBalanceByType(cardFuel, FUEL, BigDecimal.TEN);
        assertEquals(valueExpected, newBalance);
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeDrugstoreValueMoreThenBalance_ThenResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.getAdditionForBalanceByType(cardDrugstore,
                DRUGSTORE, BigDecimal.valueOf(120)));
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeFoodValueMoreThenBalance_ThenResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.getAdditionForBalanceByType(cardFood,
                FOOD, BigDecimal.valueOf((130))));
    }

    @Test
    void getAdditionForBalanceByType_WhenTypeFuelValueMoreThenBalance_ThenResponseStatusException() {
        assertThrows(ResponseStatusException.class, () -> validator.getAdditionForBalanceByType(cardFuel,
                FUEL, BigDecimal.valueOf((130))));
    }
}
