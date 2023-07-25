package br.com.alelo.consumer.consumerpat.domain.ledger.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntryOperationTypeTest {

    @Test
    void testGetEnumCredit() {
        Integer creditId = 1;
        EntryOperationType credit = EntryOperationType.getEnum(creditId);
        assertEquals(EntryOperationType.CREDIT, credit, "Enum should be CREDIT");
    }

    @Test
    void testGetEnumDebit() {
        Integer debitId = 2;
        EntryOperationType debit = EntryOperationType.getEnum(debitId);
        assertEquals(EntryOperationType.DEBIT, debit, "Enum should be DEBIT");
    }

    @Test
    void testGetEnumInvalid() {
        Integer invalidId = 3;
        DomainException exception = assertThrows(DomainException.class, () -> EntryOperationType.getEnum(invalidId));
        assertEquals("Entry operation type type not found", exception.getMessage(), "Correct exception message should be thrown");
    }

    @Test
    void testGetEnumNull() {
        DomainException exception = assertThrows(DomainException.class, () -> EntryOperationType.getEnum(null));
        assertEquals("Entry operation type type not found", exception.getMessage(), "Correct exception message should be thrown");
    }
}

