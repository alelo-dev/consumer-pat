package br.com.alelo.consumer.unit;

import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.enumeration.CardType;
import br.com.alelo.consumerpat.core.enumeration.EstablishmentType;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumerpat.core.exception.RequiredFieldsException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CardDomainTest {

    @Test
    void validateBalanceNegativeTest() {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(-1L))
                .type(CardType.DRUGSTORE)
                .build();

        assertThrows(InvalidBalanceException.class, cardDomain::validateBalance);
    }

    @Test
    void validateBalanceTest() {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(1L))
                .type(CardType.DRUGSTORE)
                .build();

        assertDoesNotThrow(cardDomain::validateBalance);
    }

    @Test
    void validateRechargeNegativeTest() {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(1L))
                .type(CardType.DRUGSTORE)
                .build();

        assertThrows(InvalidRechargeException.class, () -> cardDomain.recharge(BigDecimal.valueOf(-1L)));
    }

    @Test
    void validateRechargeTest() {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(1L))
                .type(CardType.DRUGSTORE)
                .build();

        assertDoesNotThrow(() -> cardDomain.recharge(BigDecimal.valueOf(1L)));
    }

    @Test
    void validateBalanceByFoodCardeTest() throws InvalidEstablishmentForCardException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(100L))
                .type(CardType.FOOD)
                .build();

        cardDomain.calculateBalance(EstablishmentType.FOOD, BigDecimal.valueOf(50L));

        assertEquals(BigDecimal.valueOf(55L), cardDomain.getBalance());
        assertEquals(BigDecimal.valueOf(45L), cardDomain.getValueForExtract());
    }

    @Test
    void validateBalanceByDrugstoreCardeTest() throws InvalidEstablishmentForCardException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(100L))
                .type(CardType.DRUGSTORE)
                .build();

        cardDomain.calculateBalance(EstablishmentType.DRUGSTORE, BigDecimal.valueOf(50L));

        assertEquals(BigDecimal.valueOf(50L), cardDomain.getBalance());
        assertEquals(BigDecimal.valueOf(50L), cardDomain.getValueForExtract());
    }

    @Test
    void validateBalanceByFuelCardeTest() throws InvalidEstablishmentForCardException, InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CardDomain cardDomain = CardDomain.builder()
                .balance(BigDecimal.valueOf(100L))
                .type(CardType.FUEL)
                .build();

        cardDomain.calculateBalance(EstablishmentType.FUEL, BigDecimal.valueOf(50L));

        assertEquals(new BigDecimal("32.5"), cardDomain.getBalance());
        assertEquals(new BigDecimal("67.5"), cardDomain.getValueForExtract());
    }

    @Test
    void validateRequiredFieldsTest() {
        CardDomain cardDomain = CardDomain.builder()
                .build();

        assertThrows(RequiredFieldsException.class, cardDomain::validateRequiredFields);

        try {
            cardDomain.validateRequiredFields();
        } catch (RequiredFieldsException e) {
            assertEquals(3, e.getFieldsAndMessages().size());
        }
    }
}
