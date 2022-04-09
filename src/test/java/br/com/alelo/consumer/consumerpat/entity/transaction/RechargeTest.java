package br.com.alelo.consumer.consumerpat.entity.transaction;

import br.com.alelo.consumer.consumerpat.entity.transaction.exceptions.InvalidRechargeValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RechargeTest {


    @Test
    void shouldThrowsExceptionWhenValueIsNegative() {
        assertThrows(InvalidRechargeValueException.class, () -> new Recharge(-1));
    }

}