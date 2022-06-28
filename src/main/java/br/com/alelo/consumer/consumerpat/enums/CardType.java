package br.com.alelo.consumer.consumerpat.enums;

public enum CardType {
  PHARMACY(1, "Pharmacy"),
  FOOD(2, "Food"),
  FUEL(3, "Fuel");

  private Integer cod;
  private String descricao;

  private CardType(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public int getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }

  public static CardType toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    throw new IllegalArgumentException("Id inválido: " + cod);
  }
}
