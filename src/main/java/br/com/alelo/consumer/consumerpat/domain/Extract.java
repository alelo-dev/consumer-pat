package br.com.alelo.consumer.consumerpat.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Extract {

  private String establishmentName;
  private String productDescription;
  private Date dateBuy;
  private Integer cardNumber;
  private Double value;

  public static Extract build(final PurchaseOccurrence purchaseOccurrence,
      final Double purchaseValue) {
    return Extract.builder().establishmentName(purchaseOccurrence.getEstablishmentName())
        .productDescription(purchaseOccurrence.getProductDescription()).dateBuy(new Date())
        .cardNumber(purchaseOccurrence.getCardNumber()).value(purchaseValue).build();
  }

}
