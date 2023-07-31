package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
public enum PaymentStrategy {

    STRATEGY_FOOD(CardType.FOOD) {
        @Override
        public boolean isEstablishmentAllowed(EstablishmentType establishmentType) {
            return EstablishmentType.FOOD == establishmentType;
        }

        @Override
        public BigDecimal applyDiscountRate(CardType cardType, BigDecimal amount) {
            var discountRate = 10;
            BigDecimal cashback = amount.divide(BigDecimal.valueOf(100))
                    .multiply(BigDecimal.valueOf(discountRate));
            return amount.subtract(cashback);
        }
    },
    STRATEGY_DRUGSTORE(CardType.DRUGSTORE) {
        @Override
        public boolean isEstablishmentAllowed(EstablishmentType establishmentType) {
            return EstablishmentType.DRUGSTORE == establishmentType;
        }

        @Override
        public BigDecimal applyDiscountRate(CardType cardType, BigDecimal amount) {
            return amount;
        }
    },
    STRATEGY_FUEL(CardType.FUEL) {
        @Override
        public boolean isEstablishmentAllowed(EstablishmentType establishmentType) {
            return EstablishmentType.FUEL == establishmentType;
        }

        @Override
        public BigDecimal applyDiscountRate(CardType cardType, BigDecimal amount) {
            var discountRate = 35;
            BigDecimal cashback = amount.divide(BigDecimal.valueOf(100))
                    .multiply(BigDecimal.valueOf(discountRate));
            return amount.subtract(cashback);
        }
    };

    private CardType cardType;

    PaymentStrategy(CardType cardType) {
        this.cardType = cardType;
    }

    public static PaymentStrategy getStrategy(final CardType cardType) {
        return Arrays.stream(values()).filter(enumItem -> enumItem.getCardType() == cardType).findFirst()
                .orElseThrow(() -> new DomainException("Payment type not permitted."));
    }

    public abstract boolean isEstablishmentAllowed(EstablishmentType establishmentType);

    public abstract BigDecimal applyDiscountRate(CardType cardType, BigDecimal amount);
}
