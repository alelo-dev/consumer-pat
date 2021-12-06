package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardBalanceUpdateDTO {

  private Integer cardNumber;
  private Double value;
}
