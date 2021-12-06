package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOccurrenceDTO {

  private Integer establishmentType;
  private String establishmentName;
  private Integer cardNumber;
  private String productDescription;
  private Double value;
}
