package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

  private String street;
  private Integer number;
  private String city;
  private String country;
  private Integer portalCode;
}
