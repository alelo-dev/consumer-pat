package br.com.alelo.consumer.consumerpat.sale;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.DRUGSTORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.sale.impl.DrugStoreSaleProcessor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

public class DrugStoreSaleProcessorTest extends BalancedSaleProcessorTest {

    @Getter
    private final DrugStoreSaleProcessor saleProcessor = new DrugStoreSaleProcessor();

    @Test
    public void whenDoSale_thenTransactionAndCardBalanceIsCorrect() {
        SaleData saleData = createSaleData(DRUGSTORE, 100, 30);

        Transaction transaction = saleProcessor.process(saleData);

        assertNotNull(transaction.getEstablishment());
        assertNotNull(transaction.getCard());
        assertEquals(DRUGSTORE, transaction.getProductType());
        assertEquals("description", transaction.getProductDescription());
        assertEquals(30, transaction.getValue());
        assertEquals(70, transaction.getCard().getBalance());
    }

}
