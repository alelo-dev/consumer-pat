package br.com.alelo.consumer.consumerpat.entity;

public enum CardType {

    FUEL {
        @Override
        public double calcDiscountOrTax(double value) {
            var tax  = (value / 100) * 35;
            return value + tax;
        }
    },
    DRUGSTORE,
    FOOD {
        @Override
        public double calcDiscountOrTax(double value) {
            var cashback  = (value / 100) * 10;
            return value - cashback;
        }
    };

    public double calcDiscountOrTax(double value) {
        return value;
    }
}
