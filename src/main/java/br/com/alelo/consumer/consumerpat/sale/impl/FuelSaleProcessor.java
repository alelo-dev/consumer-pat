package br.com.alelo.consumer.consumerpat.sale.impl;

import br.com.alelo.consumer.consumerpat.enums.ProductType;
import br.com.alelo.consumer.consumerpat.sale.BalanceSaleProcessor;
import org.springframework.stereotype.Component;

@Component
public class FuelSaleProcessor extends BalanceSaleProcessor {

    // acrescimo de 35%
    private static final double FUEL_TAX = 1.35;

    @Override
    public boolean supports(final ProductType productType) {
        return productType == ProductType.FUEL;
    }

    @Override
    protected double calculateSaleValue(final double saleValue) {
        return saleValue * FUEL_TAX;
    }

}
