package br.com.alelo.consumer.consumerpat.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alelo.consumer.consumerpat.domain.exception.InvalidCardException;

public class CardTypeTest {

    @Test
    @DisplayName("Should return total payment amount with 10% of discount applied when pay with food card")
    public void foodPaymentWithSucessTest() {
        BigDecimal paymentValue = new BigDecimal(100);
        int foodEstablishmentType = 1;

        BigDecimal result = CardType.FOOD.calculatePaymentAmountBy(foodEstablishmentType, paymentValue);

        BigDecimal totalAmountWithDiscount = new BigDecimal(90);
        assertEquals(totalAmountWithDiscount, result);
    }

    @Test
    @DisplayName("Should return total payment amount without any modification when pay with drugstore card")
    public void drugstorePaymentWithSucessTest() {
        BigDecimal paymentValue = new BigDecimal(100);
        int foodEstablishmentType = 2;

        BigDecimal result = CardType.DRUGSTORE.calculatePaymentAmountBy(foodEstablishmentType, paymentValue);

        BigDecimal totalAmountWithoutModification = new BigDecimal(100);
        assertEquals(totalAmountWithoutModification, result);
    }

    @Test
    @DisplayName("Should return total payment amount with additional of 35% applied when pay with fuel card")
    public void fuelPaymentWithSucessTest() {
        BigDecimal paymentValue = new BigDecimal(100);
        int foodEstablishmentType = 3;

        BigDecimal result = CardType.FUEL.calculatePaymentAmountBy(foodEstablishmentType, paymentValue);

        BigDecimal totalAmountWithAdditional = new BigDecimal(135);
        assertEquals(totalAmountWithAdditional, result);
    }

    @Test
    @DisplayName("Should throw error when try to calculate the payment for food estabilishment with an invalid card")
    void invalidCardTest() {
        InvalidCardException thrown = assertThrows(InvalidCardException.class, () -> {
            BigDecimal paymentValue = new BigDecimal(100);
            int foodEstablishmentType = 2;
    
            CardType.FOOD.calculatePaymentAmountBy(foodEstablishmentType, paymentValue);
        });

        assertTrue(thrown.getMessage().contains("Invalid card for this establishment"));
    }
}
