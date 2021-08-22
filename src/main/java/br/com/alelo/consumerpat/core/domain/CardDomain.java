package br.com.alelo.consumerpat.core.domain;

import br.com.alelo.consumerpat.core.domain.cards.Card;
import br.com.alelo.consumerpat.core.domain.cards.DrugstoreCardDomain;
import br.com.alelo.consumerpat.core.domain.cards.FoodCardDomain;
import br.com.alelo.consumerpat.core.domain.cards.FuelCardDomain;
import br.com.alelo.consumerpat.core.enumeration.CardType;
import br.com.alelo.consumerpat.core.enumeration.EstablishmentType;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumerpat.core.exception.InvalidRechargeException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDomain {

    private String card;
    private BigDecimal balance;
    private CardType type;
    private BigDecimal valueBuy;

    public void validateBalance() throws InvalidBalanceException {
        if (this.balance == null || this.balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBalanceException();
        }
    }

    public void calculateBalance(EstablishmentType establishmentType, BigDecimal value)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InvalidBalanceException, InvalidEstablishmentForCardException {
        Map<EstablishmentType, Class<? extends Card>> strategy = new HashMap<>();
        strategy.put(EstablishmentType.FOOD, FoodCardDomain.class);
        strategy.put(EstablishmentType.DRUGSTORE, DrugstoreCardDomain.class);
        strategy.put(EstablishmentType.FUEL, FuelCardDomain.class);

        if (!establishmentType.name().equals(this.type.name())) {
            throw new InvalidEstablishmentForCardException();
        }

        Card card = strategy.get(establishmentType).getDeclaredConstructor().newInstance();
        BigDecimal newBalance = card.calculateBalance(this.balance, value);
        this.valueBuy = this.balance.subtract(newBalance);
        this.balance = newBalance;
        this.validateBalance();
    }

    public void validateRecharge(BigDecimal value) throws InvalidRechargeException {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidRechargeException();
        }
    }

    public void recharge(BigDecimal value) throws InvalidRechargeException {
        this.validateRecharge(value);

        this.balance = this.balance.add(value);
    }
}
