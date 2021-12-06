package br.com.alelo.consumer.consumerpat.adapter.in.web.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtractDTO {

  private String establishmentName;
  private String productDescription;
  private Date dateBuy;
  private Integer cardNumber;
  private Double value;

}
