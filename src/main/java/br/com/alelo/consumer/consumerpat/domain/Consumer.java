package br.com.alelo.consumer.consumerpat.domain;


import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Consumer {

  private Integer id;
  private String name;
  private Integer documentNumber;
  private Date birthDate;
  private Contact contact;
  private Address address;
  private List<Card> cards;

}
