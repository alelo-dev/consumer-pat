package br.com.alelo.consumer.consumerpat.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import org.junit.jupiter.api.Test;

public abstract class BalancedSaleProcessorTest {

    @Test
    public void whenSaleValueIsBiggerThanBalance_thenThrowNotEnoughBalanceException() {
        SaleData saleData = createSaleData(ProductType.FOOD, 10, 501);

        NotEnoughBalanceException ex = assertThrows(NotEnoughBalanceException.class, () -> getSaleProcessor().process(saleData));
        assertEquals(10, ex.getCurrentBalance());
    }

    protected abstract BalanceSaleProcessor getSaleProcessor();

    protected SaleData createSaleData(ProductType productType, double currentBalance, double saleValue) {
        Establishment establishment = Establishment.builder().establishmentName("establishment").build();
        Card card = Card.builder()
                .number("55555555")
                .productType(productType)
                .balance(currentBalance)
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
