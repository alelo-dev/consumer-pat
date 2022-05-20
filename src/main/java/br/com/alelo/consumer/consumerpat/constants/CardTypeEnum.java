package br.com.alelo.consumer.consumerpat.constants;

import br.com.alelo.consumer.consumerpat.model.EstablishmentPayment;

public enum CardTypeEnum implements EstablishmentPayment {
  FOOD {
    // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
    @Override
    public double processPayment(double value) {
      Double cashback = (value / 100) * 10;
      value = value - cashback;
      return value;

    }
  },
  DRUGSTORE {
    @Override
    public double processPayment(double value) {
      return value;
    }
  },
  FUEL {
    // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
    @Override
    public double processPayment(double value) {
      Double tax = (value / 100) * 35;
      value = value + tax;
      return value;
    }
  };
}