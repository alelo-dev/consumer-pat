package br.com.alelo.consumer.consumerpat.entity;


import br.com.alelo.consumer.consumerpat.strategy.card.DrugstoreCard;
import br.com.alelo.consumer.consumerpat.strategy.card.FoodCard;
import br.com.alelo.consumer.consumerpat.strategy.card.FuelCard;
import br.com.alelo.consumer.consumerpat.strategy.card.RuleCard;

public enum CardType {
    FOOD(1L, new FoodCard()),
    DRUGSTORE(2L, new DrugstoreCard()),
    FUEL(3L, new FuelCard());

    private Long id;
    private RuleCard rule;

    CardType(Long id, RuleCard rule) {
        this.id = id;
        this.rule = rule;
    }

    public RuleCard getRuleCard() {
        return this.rule;
    }

    public Long getId() {
        return this.id;
    }

    public static CardType toEnum(Long codigo) {
        if (codigo == null) {
            return null;
        }

        for (CardType x : CardType.values()) {
            if (codigo.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("codigo inválido: " + codigo);

    }

}
