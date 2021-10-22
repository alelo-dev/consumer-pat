package br.com.alelo.consumer.consumerpat.domain.model;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.exception.InvalidCardException;

public enum CardType {

    FOOD(1) {
        @Override
        public BigDecimal calculatePaymentAmountBy(int establishmentType, BigDecimal value) {
            if (establishmentType != this.getEstablishmentTypeAllowed()) {
                throw new InvalidCardException(INVALID_CARD_ERROR_MESSAGE);
            }

            final BigDecimal discountPercentual = new BigDecimal(10);
            value = value.subtract(value.divide(PERCENTUAL_BASE).multiply(discountPercentual));
            return value;
        }
    },
    DRUGSTORE(2) {
        @Override
        public BigDecimal calculatePaymentAmountBy(int establishmentType, BigDecimal value) {
            if (establishmentType != this.getEstablishmentTypeAllowed()) {
                throw new InvalidCardException(INVALID_CARD_ERROR_MESSAGE);
            }
            return value;
        }
    },
    FUEL(3) {
        @Override
        public BigDecimal calculatePaymentAmountBy(int establishmentType, BigDecimal value) {
            if (establishmentType != this.getEstablishmentTypeAllowed()) {
                throw new InvalidCardException(INVALID_CARD_ERROR_MESSAGE);
            }

            final BigDecimal additionalPercentual = new BigDecimal(35);
            value = value.add(value.divide(PERCENTUAL_BASE).multiply(additionalPercentual));
            return value;
        }
    };

    private static final String INVALID_CARD_ERROR_MESSAGE = "Invalid card for this establishment";
    private static final BigDecimal PERCENTUAL_BASE = new BigDecimal(100);
    private int establishmentTypeAllowed;

    private CardType(int establishmentTypeAllowed) {
        this.establishmentTypeAllowed = establishmentTypeAllowed;
    }

    public int getEstablishmentTypeAllowed() {
        return this.establishmentTypeAllowed;
    }

    abstract public BigDecimal calculatePaymentAmountBy(int establishmentType, BigDecimal value);
}
