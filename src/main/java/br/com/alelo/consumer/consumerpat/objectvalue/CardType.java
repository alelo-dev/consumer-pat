package br.com.alelo.consumer.consumerpat.objectvalue;

import br.com.alelo.consumer.consumerpat.entity.CardBalanceStrategy;

public enum CardType implements CardBalanceStrategy {
    FOOD{
        @Override
        public double calculateTransactionValue(double value) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            return value - cashback;
        }

        @Override
        public boolean isEstablishmentAllowed(int establishmentId) {
            return EstablishmentType.FOOD.getValue() == establishmentId;
        }
    },
    DRUGSTORE{
        @Override
        public double calculateTransactionValue(double value) {
            return value;
        }

        @Override
        public boolean isEstablishmentAllowed(int establishmentId) {
            return EstablishmentType.DRUGSTORE.getValue() == establishmentId;
        }
    },
    FUEL{
        @Override
        public double calculateTransactionValue(double value) {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            return value + tax;
        }

        @Override
        public boolean isEstablishmentAllowed(int establishmentId) {
            return EstablishmentType.FUEL.getValue() == establishmentId;
        }
    };


}
