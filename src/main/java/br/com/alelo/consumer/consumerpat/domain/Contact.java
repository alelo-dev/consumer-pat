package br.com.alelo.consumer.consumerpat.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {

  private Integer mobilePhoneNumber;
  private Integer residencePhoneNumber;
  private Integer phoneNumber;
  private String email;
}
