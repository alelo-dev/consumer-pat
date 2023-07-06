package br.com.alelo.consumer.consumerpat.domain;

import br.com.alelo.consumer.consumerpat.domain.base.EstablishmentCost;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import lombok.SneakyThrows;

public enum EstablishmentTypeEnum implements EstablishmentCost {
    FOOD(1){

        public Consumer updateBalance(Double value, Integer cardNumber, Consumer consumer){

            isCardValid(cardNumber, consumer.getFoodCardNumber());

            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            value -= (value / 100) * 10;

            consumer.setFoodCardBalance(
                    subtractBalance(consumer.getFoodCardBalance(), value));

            return consumer;
        }

    },
    DRUGSTORE(2){
        @Override
        public Consumer updateBalance(Double value, Integer cardNumber, Consumer consumer) {

            isCardValid(cardNumber, consumer.getDrugstoreNumber());

            consumer.setDrugstoreCardBalance(
                    subtractBalance(consumer.getDrugstoreCardBalance(), value));

            return consumer;
        }
    },
    FUEL(3){
        @Override
        public Consumer updateBalance(Double value, Integer cardNumber, Consumer consumer) {

            isCardValid(cardNumber, consumer.getFuelCardNumber());

            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            value += (value / 100) * 35;

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
    private static double subtractBalance(Double balance, Double value) {
        if (balance < value)
            throw new BadRequestException("Insufficient balance for this operation");

        return balance - value;
    }
}
