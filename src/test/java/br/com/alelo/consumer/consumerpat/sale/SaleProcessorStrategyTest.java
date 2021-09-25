package br.com.alelo.consumer.consumerpat.sale;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.DRUGSTORE;
import static br.com.alelo.consumer.consumerpat.enums.ProductType.FOOD;
import static br.com.alelo.consumer.consumerpat.enums.ProductType.FUEL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaleProcessorStrategyTest {

    @Autowired
    private SaleProcessorStrategy saleProcessorStrategy;

    @Test
    public void whenSaleIsFood_thenReturnCorrectTransaction() {
        SaleData saleData = createSaleData(FOOD, 100);

        Transaction transaction = saleProcessorStrategy.processSale(saleData);

        assertNotNull(transaction.getEstablishment());
        assertNotNull(transaction.getCard());
        assertEquals(FOOD, transaction.getProductType());
        assertEquals("description", transaction.getProductDescription());
        assertEquals(90, transaction.getValue());
    }

    @Test
    public void whenSaleIsDrugStore_thenReturnCorrectTransaction() {
        SaleData saleData = createSaleData(DRUGSTORE, 100);

        Transaction transaction = saleProcessorStrategy.processSale(saleData);

        assertNotNull(transaction.getEstablishment());
        assertNotNull(transaction.getCard());
        assertEquals(DRUGSTORE, transaction.getProductType());
        assertEquals("description", transaction.getProductDescription());
        assertEquals(100, transaction.getValue());
    }

    @Test
    public void whenSaleIsFuel_thenReturnCorrectTransaction() {
        SaleData saleData = createSaleData(FUEL, 100);

        Transaction transaction = saleProcessorStrategy.processSale(saleData);

        assertNotNull(transaction.getEstablishment());
        assertNotNull(transaction.getCard());
        assertEquals(FUEL, transaction.getProductType());
        assertEquals("description", transaction.getProductDescription());
        assertEquals(135, transaction.getValue());
    }

    private SaleData createSaleData(ProductType productType, double saleValue) {
        Establishment establishment = Establishment.builder().establishmentName("establishment").build();
        Card card = Card.builder()
                .number("55555555")
                .productType(productType)
                .balance(500)
                .build();

        return SaleData.builder()
                .establishment(establishment)
                .card(card)
                .productType(productType)
                .productDescription("description")
                .saleValue(saleValue)
                .build();
    }

}
