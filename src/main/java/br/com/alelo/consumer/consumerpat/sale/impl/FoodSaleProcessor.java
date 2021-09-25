package br.com.alelo.consumer.consumerpat.sale.impl;

import br.com.alelo.consumer.consumerpat.enums.ProductType;
import br.com.alelo.consumer.consumerpat.sale.BalanceSaleProcessor;
import org.springframework.stereotype.Component;

@Component
public class FoodSaleProcessor extends BalanceSaleProcessor {

    // 10%
    private static final double FOOD_DISCOUNT = 0.1;

    @Override
    public boolean supports(final ProductType productType) {
        return productType == ProductType.FOOD;
    }

    @Override
    protected double calculateSaleValue(final double saleValue) {
        return saleValue - (saleValue * FOOD_DISCOUNT);
    }

}
