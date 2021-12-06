package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumerDTO {
  
  private String name;
  private Integer documentNumber;
  private Date birthDate;
  private ContactDTO contact;
  private AddressDTO address;
  private List<CardDTO> cards;

}
