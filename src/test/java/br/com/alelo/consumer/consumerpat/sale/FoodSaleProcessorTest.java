package br.com.alelo.consumer.consumerpat.sale;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.FOOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.sale.impl.FoodSaleProcessor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

public class FoodSaleProcessorTest extends BalancedSaleProcessorTest {

    @Getter
    private final FoodSaleProcessor saleProcessor = new FoodSaleProcessor();

    @Test
    public void whenDoSale_thenTransactionAndCardBalanceIsCorrect() {
        SaleData saleData = createSaleData(FOOD, 100, 50);

        Transaction transaction = saleProcessor.process(saleData);

        assertNotNull(transaction.getEstablishment());
        assertNotNull(transaction.getCard());
        assertEquals(FOOD, transaction.getProductType());
        assertEquals("description", transaction.getProductDescription());
        assertEquals(45, transaction.getValue());
        assertEquals(55, transaction.getCard().getBalance());
    }

}
