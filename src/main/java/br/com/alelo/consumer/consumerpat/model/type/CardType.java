package br.com.alelo.consumer.consumerpat.model.type;

import java.math.BigDecimal;

public enum CardType {
// index 0 food
    FOOD {
        @Override
        public BigDecimal getTax() {
            return new BigDecimal(0.9);
        }
    },

    // index 1 drugstore
    DRUGSTTORE {
        @Override
        public BigDecimal getTax() {
            return new BigDecimal(1);
        }
    },

    // index 2 fuel
    FUEL {
        @Override
        public BigDecimal getTax() {
            return new BigDecimal(1.35);
        }
    };

    public abstract BigDecimal getTax();

}
