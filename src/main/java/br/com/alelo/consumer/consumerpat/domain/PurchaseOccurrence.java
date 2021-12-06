package br.com.alelo.consumer.consumerpat.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOccurrence {

  private Integer establishmentType;
  private String establishmentName;
  private String productDescription;
  private Integer cardNumber;
  private Double value;

}
