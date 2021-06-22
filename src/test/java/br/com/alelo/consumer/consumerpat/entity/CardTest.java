package br.com.alelo.consumer.consumerpat.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void registerPurchaseShouldSubtractProperly() {
        Card card = new Card();
        card.setBalance(BigDecimal.TEN);

        card.registerPurchase(BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(9), card.getBalance());

        card.registerPurchase(BigDecimal.valueOf(3.3));
        assertEquals(BigDecimal.valueOf(5.7), card.getBalance());

        card.registerPurchase(BigDecimal.valueOf(1.7));
        assertEquals(BigDecimal.valueOf(4.0), card.getBalance());
    }
}