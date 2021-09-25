package br.com.alelo.consumer.consumerpat.sale;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.FUEL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.sale.impl.FuelSaleProcessor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

public class FuelSaleProcessorTest extends BalancedSaleProcessorTest {

    @Getter
    private final FuelSaleProcessor saleProcessor = new FuelSaleProcessor();

    @Test
    public void whenDoSale_thenTransactionAndCardBalanceIsCorrect() {
        SaleData saleData = createSaleData(FUEL, 300, 100);

        Transaction transaction = saleProcessor.process(saleData);

        assertNotNull(transaction.getEstablishment());
        assertNotNull(transaction.getCard());
        assertEquals(FUEL, transaction.getProductType());
        assertEquals("description", transaction.getProductDescription());
        assertEquals(135, transaction.getValue());
        assertEquals(165, transaction.getCard().getBalance());
    }

}
