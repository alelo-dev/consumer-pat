package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstablishmentTypeTest {

    @Test
    void testGetEnumFood() {
        Integer foodId = 1;
        EstablishmentType food = EstablishmentType.getEnum(foodId);
        assertEquals(EstablishmentType.FOOD, food, "Enum should be FOOD");
    }

    @Test
    void testGetEnumDrugstore() {
        Integer drugstoreId = 2;
        EstablishmentType drugstore = EstablishmentType.getEnum(drugstoreId);
        assertEquals(EstablishmentType.DRUGSTORE, drugstore, "Enum should be DRUGSTORE");
    }

    @Test
    void testGetEnumFuel() {
        Integer fuelId = 3;
        EstablishmentType fuel = EstablishmentType.getEnum(fuelId);
        assertEquals(EstablishmentType.FUEL, fuel, "Enum should be FUEL");
    }

    @Test
    void testGetEnumInvalid() {
        Integer invalidId = 4;
        DomainException exception = assertThrows(DomainException.class, () -> EstablishmentType.getEnum(invalidId));
        assertEquals("Establishment type not found", exception.getMessage(), "Correct exception message should be thrown");
    }

    @Test
    void testGetEnumNull() {
        DomainException exception = assertThrows(DomainException.class, () -> EstablishmentType.getEnum(null));
        assertEquals("Establishment type not found", exception.getMessage(), "Correct exception message should be thrown");
    }
}
