package br.com.alelo.consumer.consumerpat.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {

  private Integer number;
  private Double balance;
  private CardType type;
}