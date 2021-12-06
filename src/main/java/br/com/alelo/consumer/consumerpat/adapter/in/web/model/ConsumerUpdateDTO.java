package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumerUpdateDTO {

  private Integer id;
  private String name;
  private Integer documentNumber;
  private Date birthDate;
  private ContactDTO contact;
  private AddressDTO address;
}
