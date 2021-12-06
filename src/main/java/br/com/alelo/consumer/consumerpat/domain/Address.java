package br.com.alelo.consumer.consumerpat.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

  private String street;
  private Integer number;
  private String city;
  private String country;
  private Integer portalCode;
}
