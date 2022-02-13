package br.com.alelo.consumer.consumerpat.contants;

import br.com.alelo.consumer.consumerpat.business.Rule;
import br.com.alelo.consumer.consumerpat.entity.Card;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
 *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
 *
 * Tipos de estabelcimentos
 * 1 - Alimentação (food)
 * 2 - Farmácia (DrugStore)
 * 3 - Posto de combustivel (Fuel)
 */
@Getter
public enum EstablishmentType implements Rule {
    FOOD(CardType.FOOD_CARD){
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        public Double applyBuyRule(Double value) {
            Double cashback  = (value / 100) * 10;
            return value - cashback;
        }
    },
    DRUGSTORE(CardType.DRUGSTORE_CARD){
        public Double applyBuyRule(Double value) {return value;}
    },
    FUEL(CardType.FUEL_CARD){
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        public Double applyBuyRule(Double value) {
            Double tax  = (value / 100) * 35;
            return value + tax;
        }
    };

    private CardType cardTypeAllowed;

    EstablishmentType(CardType cardTypeAllowed){
        this.cardTypeAllowed = cardTypeAllowed;
    }

    @JsonValue
    public String toValue() {
        return toString();
    }
}
