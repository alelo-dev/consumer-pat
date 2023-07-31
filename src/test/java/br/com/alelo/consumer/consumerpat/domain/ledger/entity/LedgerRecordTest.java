package br.com.alelo.consumer.consumerpat.domain.ledger.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerRecordTest {

    @Test
    void testLedgerRecordConstructorAndGetters() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = new BigDecimal("100.0");
        EntryOperationType entryOperationType = EntryOperationType.CREDIT;

        LedgerRecord ledgerRecord = new LedgerRecord(cardNumber, amount, entryOperationType);

        assertEquals(cardNumber, ledgerRecord.getCardNumber(), "Card number should be the same");
        assertEquals(amount, ledgerRecord.getAmount(), "Amount should be the same");
        assertEquals(entryOperationType, ledgerRecord.getEntryOperationType(), "Entry operation type should be the same");
        assertNotNull(ledgerRecord.getEntryOperationDate(), "Entry operation date should not be null");
    }

    @Test
    void testLedgerRecordToString() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = new BigDecimal("100.0");
        EntryOperationType entryOperationType = EntryOperationType.CREDIT;

        LedgerRecord ledgerRecord = new LedgerRecord(cardNumber, amount, entryOperationType);

        String expectedToString = "LedgerRecord(cardNumber=" + cardNumber + ", amount=100.0, entryOperationType=CREDIT, entryOperationDate=" + ledgerRecord.getEntryOperationDate() + ")";
        assertEquals(expectedToString, ledgerRecord.toString(), "toString() should match the expected string");
    }

    @Test
    void testLedgerRecordEqualsAndHashCode() {
        var cardNumber1 = new CardNumber("1234567812345678");
        BigDecimal amount1 = new BigDecimal("100.0");
        EntryOperationType entryOperationType1 = EntryOperationType.CREDIT;

        LedgerRecord ledgerRecord1 = new LedgerRecord(cardNumber1, amount1, entryOperationType1);

        var cardNumber2 = new CardNumber("8765432187654321");
        BigDecimal amount2 = new BigDecimal("100.0");
        EntryOperationType entryOperationType2 = EntryOperationType.DEBIT;

        LedgerRecord ledgerRecord2 = new LedgerRecord(cardNumber2, amount2, entryOperationType2);

        assertNotEquals(ledgerRecord1, ledgerRecord2, "Ledger records with different values should not be equal");
        assertNotEquals(ledgerRecord1.hashCode(), ledgerRecord2.hashCode(), "HashCode of different ledger records should not be equal");

        assertEquals(ledgerRecord1, ledgerRecord1, "A ledger record should be equal to itself");
        assertEquals(ledgerRecord1.hashCode(), ledgerRecord1.hashCode(), "HashCode of a ledger record should be consistent");
    }
}
