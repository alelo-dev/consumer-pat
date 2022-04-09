package br.com.alelo.consumer.consumerpat.entity.transaction;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.transaction.exceptions.InvalidDebitValueException;
import br.com.alelo.consumer.consumerpat.entity.transaction.exceptions.NegativeBalanceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebitTest {

    @Test
    void shouldThrowsExceptionWhenValueIsNegative() {
        assertThrows(InvalidDebitValueException.class, () -> new Debit(-1, CardType.DRUGSTORE));
    }

    @Test
    void shouldThrowsExceptionWhenBalanceIsNegative() {
        var debit =  new Debit(10, CardType.DRUGSTORE);
        assertThrows(NegativeBalanceException.class, () -> debit.apply( 5));
    }
}