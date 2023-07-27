package br.com.alelo.consumer.consumerpat.domain.card.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CardBalanceTest {

    private Validator validator;

    public CardBalanceTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCardBalanceCreationValid() {
        UUID cardId = UUID.randomUUID();
        Card card = new Card(new CardNumber("1234567812345678"), CardType.FOOD);
        CardBalance cardBalance = new CardBalance(cardId, card);

        assertDoesNotThrow(() -> validator.validate(cardBalance));
    }

    @Test
    void testChargeCardBalance() {
        UUID cardId = UUID.randomUUID();
        Card card = new Card(new CardNumber("1234567812345678"), CardType.FOOD);
        CardBalance cardBalance = new CardBalance(cardId, card);

        BigDecimal initialBalance = new BigDecimal("100.0");
        cardBalance.chargeCardBalance(initialBalance);

        assertEquals(initialBalance, cardBalance.getAmount());

        BigDecimal additionalCharge = new BigDecimal("50.0");
        BigDecimal expectedBalance = initialBalance.add(additionalCharge);
        cardBalance.chargeCardBalance(additionalCharge);

        assertEquals(expectedBalance, cardBalance.getAmount());
    }

    @Test
    void testWithdrawCardBalance() {
        UUID cardId = UUID.randomUUID();
        Card card = new Card(new CardNumber("1234567812345678"), CardType.FOOD);
        CardBalance cardBalance = new CardBalance(cardId, card);

        BigDecimal initialBalance = new BigDecimal("100.0");
        cardBalance.chargeCardBalance(initialBalance);

        BigDecimal withdrawalAmount = new BigDecimal("50.0");
        cardBalance.withdrawCardBalance(withdrawalAmount);

        assertEquals(initialBalance.subtract(withdrawalAmount), cardBalance.getAmount());
    }

    @Test
    void testWithdrawCardBalanceInsufficientFunds() {
        UUID cardId = UUID.randomUUID();
        Card card = new Card(new CardNumber("1234567812345678"), CardType.FOOD);
        CardBalance cardBalance = new CardBalance(cardId, card);

        BigDecimal initialBalance = new BigDecimal("100.0");
        cardBalance.chargeCardBalance(initialBalance);

        BigDecimal withdrawalAmount = new BigDecimal("150.0");

        assertThrows(DomainException.class, () -> cardBalance.withdrawCardBalance(withdrawalAmount));
    }
}
