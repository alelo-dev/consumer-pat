package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {

  private Integer mobilePhoneNumber;
  private Integer residencePhoneNumber;
  private Integer phoneNumber;
  private String email;

}
