package br.com.alelo.consumer.consumerpat.enums;

public enum CashBackOrTax{
    FOOD_CASHBACK(10), FUEL_TAX(35);

    public final int percent;

    private CashBackOrTax(int percent) {
        this.percent = percent;
    }    
}