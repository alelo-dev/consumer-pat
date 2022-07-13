package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.models.Compra;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author renanravelli
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CompraJsonRequest", description = "Informa\u00E7\u00F5es para realizar uma compra.")
public class CompraJsonRequest {

  @ApiModelProperty(name = "establishmentType", value = "Establishment type", example = "1", position = 1)
  private int establishmentType;

  @ApiModelProperty(name = "establishmentName", value = "Establishment name", example = "Example", position = 2)
  private String establishmentName;

  @ApiModelProperty(name = "productDescription", value = "Product description", example = "Example product", position = 3)
  private String productDescription;

  @ApiModelProperty(name = "value", value = "Value", example = "100", position = 4)
  private BigDecimal value;

  public Compra toModel() {
    return Compra.builder()
        .establishmentType(establishmentType)
        .establishmentName(establishmentName)
        .productDescription(productDescription)
        .value(value)
        .build();
  }

}
