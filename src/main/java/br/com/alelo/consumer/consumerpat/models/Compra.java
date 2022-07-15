package br.com.alelo.consumer.consumerpat.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author renanravelli
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

  private int establishmentType;

  private String establishmentName;

  private String productDescription;

  private BigDecimal value;

  public boolean isTypeFood() {
    return this.establishmentType == 1;
  }

  public boolean isTypeDrugstore() {
    return this.establishmentType == 2;
  }

  public boolean isTypeFuel() {
    return this.establishmentType == 3;
  }

}
