package br.com.alelo.consumer.consumerpat.sale;

public abstract class OwnValueSaleProcessor extends BalanceSaleProcessor {

    @Override
    protected double calculateSaleValue(final double saleValue) {
        return saleValue;
    }
}
