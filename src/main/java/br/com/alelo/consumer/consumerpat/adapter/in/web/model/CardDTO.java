package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CardDTO {
  
  private Integer number;
  private Double balance;
  private String type;
}
