package br.com.alelo.consumer.consumerpat.domain;

import br.com.alelo.consumer.consumerpat.domain.base.EstablishmentCost;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import lombok.SneakyThrows;

import java.math.BigDecimal;

public enum EstablishmentTypeEnum implements EstablishmentCost {
    FOOD(1){

        @Override
        public Consumer updateBalance(BigDecimal value, Integer cardNumber, Consumer consumer){

            isCardValid(cardNumber, consumer.getFoodCardNumber());

            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            value.subtract(value
                    .divideToIntegralValue(BigDecimal.valueOf(100.0))
                    .multiply(BigDecimal.valueOf(10)));

            consumer.setFoodCardBalance(
                    subtractBalance(consumer.getFoodCardBalance(), value));

            return consumer;
        }

    },
    DRUGSTORE(2){
        @Override
        public Consumer updateBalance(BigDecimal value, Integer cardNumber, Consumer consumer) {

            isCardValid(cardNumber, consumer.getDrugstoreNumber());

            consumer.setDrugstoreCardBalance(
                    subtractBalance(consumer.getDrugstoreCardBalance(), value));

            return consumer;
        }
    },
    FUEL(3){
        @Override
        public Consumer updateBalance(BigDecimal value, Integer cardNumber, Consumer consumer) {

            isCardValid(cardNumber, consumer.getFuelCardNumber());

            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            value.add(value
                    .divideToIntegralValue(BigDecimal.valueOf(100.0))
                    .multiply(BigDecimal.valueOf(35)));

            consumer.setFuelCardBalance(
                    subtractBalance(consumer.getFuelCardBalance(), value));

            return consumer;

        }
    };

    private Integer id;

    EstablishmentTypeEnum(Integer id) {
        this.id = id;
    }

    public Integer getDescription() {
        return id;
    }

    @SneakyThrows
    public static void isCardValid(Integer cardNumber, Integer otherCardNumber) {
        if(cardNumber != otherCardNumber)
            throw new BadRequestException("Invalid card for this type of operation");
    }

    @SneakyThrows
    private static BigDecimal subtractBalance(BigDecimal balance, BigDecimal value) {
        if (balance.compareTo(value) == -1)
            throw new BadRequestException("Insufficient balance for this operation");

        return balance.subtract(value);
    }

    @SneakyThrows
    public static EstablishmentTypeEnum valueOf(Integer id) {
        for (EstablishmentTypeEnum type : EstablishmentTypeEnum.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new BadRequestException("Invalid Establishment Type: " + id);
    }
}
