package br.com.alelo.consumer.consumerpat.model;

public enum EstablishmentType {

  /* Tipos de estabelcimentos
   * 1 - Alimentação (food)
   * 2 - Farmácia (DrugStore)
   * 3 - Posto de combustivel (Fuel)
   */
  FOOD(1) {
    @Override
    public Double paymentCalculator(Double value) {
      // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
      Double cashback = (value / 100) * 10;
      value = value - cashback;
      return value;
    }
  },

  DRUG_STORE(2) {
    @Override
    // Para compras no cartão de farmácia o cliente não recebe desconto
    public Double paymentCalculator(Double value) {
      return value;
    }
  },

  FUEL(3) {
    @Override
    public Double paymentCalculator(Double value) {
      // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
      Double tax = (value / 100) * 35;
      value = value + tax;
      return value;
    }
  };

  public int establishmentType;

  EstablishmentType(int value) {
    this.establishmentType = value;
  }

  /*
   * Recupera o Enum pelo valor
   */
  public static EstablishmentType getByValue(int value)  {
    for (EstablishmentType type : EstablishmentType.values()) {
      if (type.establishmentType == value) {
        return type;
      }
    }
    return null;
  }

  /*
   * Calcula o desconto
   */
  abstract public Double paymentCalculator( Double value);
}
